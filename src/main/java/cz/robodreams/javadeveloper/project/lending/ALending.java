package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.article.articlebooks.BookImpl;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.*;
import cz.robodreams.javadeveloper.project.users.UserImpl;
import cz.robodreams.javadeveloper.project.users.Users;

import java.util.List;

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

    public void showBorrowedBooks() {

        //repository.values().stream()
        repository.stream()
                .filter(x -> !x.getBook().getBorrowed())
                .forEach(x -> {
                    Util.line();
                    x.getBook().show(ShowSubjectItems.LONG_FORMAT);
                });
    }


    public void showUsersBorrowedBooks() {

        Util.line();

        // uživatelé s půjčenými knihami
        //List<Integer> usr = repository.values().stream()
        List<Integer> usr = repository.stream()
                .map(x -> x.getUser().getId())
                .distinct()
                .toList();

        // které knihy mají půjčené
        usr.forEach(x -> {
            System.out.println(((UserImpl) users.get(x)).getShortInfo() + " má zapůjčené: ");

            //repository.values().stream()
            repository.stream()
                    .filter(y -> x.equals(y.getUser().getId()))
                    .forEach(y -> System.out.println(
                            "|\t" + ((BookImpl) y.getBook()).getShortInfo() + " " +
                                    ((ALoan) y).getShortInfo())
                    );
            Util.line();
        });
        System.out.println(" ... konec. ");
    }

}
