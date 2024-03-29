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

        System.out.println("repository LOAN " + repository.size());

        loan.setStatus(ILendingConst.STATUS_BORROW.BOOK_RETURNED);
        boolean b = repository.remove(loan);

        System.out.println("repository LOAN " + repository.size());

        return b;

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


//
//    public void showUsersBorrowedBooks() {
//
//        Util.line();
//
//        // uživatelé s půjčenými knihami
//        //List<Integer> usr = repository.values().stream()
//        List<Integer> usr = repository.stream()
//                .map(x -> x.getUser().getId())
//                .distinct()
//                .toList();
//
//        // které knihy mají půjčené
//        usr.forEach(x -> {
//            System.out.println(((UserImpl) users.get(x)).getResultShow(ShowSubjectItems.INFO) + " má zapůjčené: ");
//
//            //repository.values().stream()
//            repository.stream()
//                    .filter(y -> x.equals(y.getUser().getId()))
//                    .forEach(y -> System.out.println(
//                            "|\t" + ((BookImpl) y.getBook()).getResultShow(ShowSubjectItems.SHORT_FORMAT) + " " +
//                                    ((ALoan) y).getResultShow(ShowSubjectItems.SHORT_FORMAT))
//                    );
//            Util.line();
//        });
//        System.out.println(" ... konec. ");
//    }


    public List<String> showBorrowedBooks(ShowSubjectItems showItems) {

        List<String> result = new ArrayList<>();
//        result.add(Util.getLine());

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

            //          result.add(Util.getLine());

//            result.add(Util.getLine());

        });
        System.out.println(" ... konec. ");
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

//            return repository.stream()
//                    .map(x -> ((User)x.getUser()))
//                    .distinct()
//                    .collect(Collectors.toMap(x->(counter.getAndIncrement()),Function.identity() ));
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
                    //.map(x -> ((Book) x.getBook()))
                    .map(x-> ((ILoan)x))
                    .collect(Collectors.toMap(x -> (counter.getAndIncrement()), Function.identity()));

//
//            List<String> result = new ArrayList<>();
////        result.add(Util.getLine());
//
//            // uživatelé s půjčenými knihami
//            List<Integer> usr = repository.stream()
//                    .map(x -> x.getUser().getId())
//                    .distinct()
//                    .toList();
//
//            // které knihy mají půjčené
//            usr.forEach(x -> {
//                result.add(Util.getLine());
//                result.add(users.get(x).getShortInfo() + " má zapůjčené: ");
//                result.addAll(repository.stream()
//                        .filter(y -> x.equals(y.getUser().getId()))
//                        .map(y -> y.getBook().getShortInfo() + y.getShortInfo()
//                        ).toList());
//
//                //          result.add(Util.getLine());
//
////            result.add(Util.getLine());
//
//            });
//            System.out.println(" ... konec. ");
//            return result;


        } catch (RuntimeException e) {
            return new HashMap<>();
        }
    }
//
//
//
//
//
//
//
//            System.out.println(" dd " + dd.size());
//
//            dd.forEach((id, count) -> System.out.println("ID: " + id + ", Počet: " + count));
//
//
//
//            return dd;
//


}


//            Map<Integer,List<User>>  dd = repository.stream()
//                    .filter(items -> ((Book)items.getBook()).getBorrowed())
//                    .map(items -> items.getUser().getId())
//                    //.flatMap(Collection::stream)
//                    //.collect(Collectors.groupingBy(User::getId, toList()));
//                    .collect(Collectors.toMap((x,y) -> (counter.getAndIncrement(), y)));  //Function.identity()


//            List<User> repository = new HashMap<>();
//            AtomicInteger counter = new AtomicInteger(1);

//.collect(Collectors.toMap(counter.getAndIncrement(),Function.identity() ));

//                    .collect(Collectors.toMap(
//                            Entry::getKey, entry -> entry.getValue().getTotalSegmentSize()));
//
//                    //.filter(items -> ((Book)items).getBorrowed())
//                    //.map(x -> ((User)x).getId())
//                    .flatMap(Collection::stream)
//                    .collect(Collectors.toMap(counter.getAndIncrement(), User::getId, (k, k2) -> k, HashMap::new));  // Function.identity()
//
//                    .collect(Collectors.groupingBy(
//                            items -> items.
//
//                                    .getUser().getId(),
//                            HashMap::new,
//                            Collectors.counting()
//                    ));


//
//                    .map(items -> items.getUser().getId())
//                    //.flatMap(Collection::stream)
//                    //.collect(Collectors.groupingBy(User::getId, toList()));
//                    .collect(Collectors.toMap((x,y) -> (counter.getAndIncrement(), y)));  //Function.identity()
//
//
//
//            List<User> repository = new HashMap<>();
//            AtomicInteger counter = new AtomicInteger(1);
//
//            Map<Integer,Integer>  dd = repository.stream()
//                    .filter(items -> ((Book)items.getBook()).getBorrowed())
//                    .map(items -> items.getUser().getId())
//                    .collect(Collectors.groupingBy(User::getId, ??? ));
//
//
//                    .collect(Collectors.toMap((x,y) -> (counter.getAndIncrement(), Function.identity())));


//        List<String> listOfAll = repository.stream()
//                .filter(y -> y.getUser().getId().equals(user.getId()))
//                .map(y -> y.getBook().getShortInfo() + y.getShortInfo())
//                .toList();
//
//        List<String> result = new ArrayList<>();
//        result.add(Util.getLine());
//        if (listOfAll.isEmpty()) {
//
//            result.add(users.get(user.getId()).getShortInfo() + " nemá evidované půjčení knihy.");
//        } else {
//
//            result.add(users.get(user.getId()).getShortInfo() + " má evidované tyto knihy.");
//            result.addAll(listOfAll);
//        }
//
//        return result;

//
//    public Map<Integer,Book> showFreeBooks(ShowSubjectItems showSubjectItems) {
//
//        try {
//            AtomicInteger counter = new AtomicInteger(1);
//            return repository.stream()
//                    .filter(items -> items.getArticleType() == ArticleType.BOOKS)
//                    .filter(items -> items.getLocked() == Lock.UNLOCK)
//                    .filter(items -> !items.getBorrowed())
//                    .map(items -> ((Book) items))
//                    .collect(Collectors.toMap(x -> (counter.getAndIncrement()), Function.identity()));
//
////                    this.<Book>getList(Lock.LOCK, ArticleType.BOOKS).stream()
////                    .filter(items -> (testGenre.test(genre, items.getGenre())))
////                    .collect(Collectors.toMap(x -> (counter.getAndIncrement()), Function.identity()));
//
//        } catch (RuntimeException e) {
//            return new HashMap<>();
//        }
//    }



