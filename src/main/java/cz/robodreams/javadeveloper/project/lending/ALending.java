package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.*;
import cz.robodreams.javadeveloper.project.users.User;
import cz.robodreams.javadeveloper.project.users.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ALending extends SubjectsImpl<ILoan> implements Lending<ILoan>, SubjectAdd<ILoan> {

    private Users users;
    private ArticlesRepository articlesRepository;

    public ALending() {
        this.articlesRepository = Service.getInstance().getArticle();
        this.users = Service.getInstance().getUser();
    }

    @Override
    public void generator(int count) {
        new ALendingGenerator(this, count);
    }

    public Boolean newLoan(User user, Book book) {

        if (!book.getBorrowed()) {
            synchronized (book) {
                ILoan loan = ALoan.builder()
                        .user(user)
                        .book(book)
                        .sinceWhen(LocalDateTime.now())
                        .untilWhen(LocalDateTime.now().plusDays(ILendingConst.BORROWED_LEADTIME))
                        .status(ILendingConst.STATUS_BORROW.BOOK_BORROWED).build();
                loan.setStatus(ILendingConst.STATUS_BORROW.BOOK_BORROWED);
                repository.add(loan);
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean removeLoan(ILoan loan){

        loan.setStatus(ILendingConst.STATUS_BORROW.BOOK_RETURNED);
        return repository.remove(loan);
    }

    @Override
    public void show(int id, ShowSubjectItems showItems) {
        Util.line();
        get(id).show(showItems);
    }


    @Override
    public ArticleType getArticleType() {
        return null;
    }

    @Override
    public Integer getId() {
        return null;
    }

    public List<String> showBorrowedBooks(ShowSubjectItems showItems) {

        List<String> result = new ArrayList<>();

        // uživatelé s půjčenými knihami
        List<Integer> usr = repository.stream()
                .map(x -> x.getUser().getId())
                .distinct()
                .toList();

        // které knihy mají půjčené
        usr.forEach(x -> {
            result.add(Util.getLine());
            result.add(users.get(x).getShortInfo() + " má zapůjčené: ");
            result.addAll(repository.stream()
                    .filter(y -> x.equals(y.getUser().getId()))
                    .map(y -> y.getBook().getShortInfo() + y.getShortInfo()
                    ).toList());
        });
        return result;
    }


    @Override
    public List<String> showBorrowedBooksFromUser(User user) {

        List<String> listOfAll = repository.stream()
                .filter(y -> y.getUser().getId().equals(user.getId()))
                .map(y -> y.getBook().getShortInfo() + y.getShortInfo())
                .toList();

        List<String> result = new ArrayList<>();
        result.add(Util.getLine());

        if (listOfAll.isEmpty()) {
            result.add(users.get(user.getId()).getShortInfo() + " nemá evidované půjčení knihy.");
        } else {
            result.add(users.get(user.getId()).getShortInfo() + " má evidované tyto knihy.");
            result.addAll(listOfAll);
        }
        return result;
    }

    @Override
    public Map<User, Long> driverReleaseClientSelection() {

        try {
            AtomicInteger counter = new AtomicInteger(1);
            return repository.stream()
                    .map(ILoan::getUser)
                    .collect(Collectors.groupingBy(Function.identity(), (Collectors.counting())));
        } catch (RuntimeException e) {
            return new HashMap<>();
        }
    }

    @Override
    public Map<Integer, ILoan> driverReleaseBooks(Integer userId) {
        try {
            AtomicInteger counter = new AtomicInteger(1);
            return repository.stream()
                    .filter(x -> (((User) x.getUser()).getId() == userId))
                    .map(x-> ((ILoan)x))
                    .collect(Collectors.toMap(x -> (counter.getAndIncrement()), Function.identity()));

        } catch (RuntimeException e) {
            return new HashMap<>();
        }
    }
}

