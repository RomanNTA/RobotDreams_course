package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.article.articlebooks.BookImpl;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;
import cz.robodreams.javadeveloper.project.common.SubjectsImpl;
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
//        new ALendingGenerator(this, count, users, books);
    }


    @Override
    public void show(int id, ShowSubjectItems showItems) {
        line();
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
                    this.line();
                    x.getBook().show(ShowSubjectItems.LONG_FORMAT);
                });
    }


    public void showUsersBorrowedBooks() {

        this.line();

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
            this.line();
        });
        System.out.println(" ... konec. ");
    }

}
