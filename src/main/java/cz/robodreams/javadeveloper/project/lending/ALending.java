package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.books.Book;
import cz.robodreams.javadeveloper.project.books.IBook;
import cz.robodreams.javadeveloper.project.books.IBooks;
import cz.robodreams.javadeveloper.project.common.ASubject;
import cz.robodreams.javadeveloper.project.common.ISubjectAdd;
import cz.robodreams.javadeveloper.project.users.AUser;
import cz.robodreams.javadeveloper.project.users.IUser;
import cz.robodreams.javadeveloper.project.users.IUsers;

import java.util.List;

public class ALending extends ASubject<ILoan> implements ILending<ILoan>, ISubjectAdd<ILoan> {

    private IUsers<IUser> users;
    private IBooks<IBook> books;

    public ALending(IUsers<IUser> users, IBooks<IBook> books) {
        this.books = books;
        this.users = users;
    }

    @Override
    public void generator(int count) {
        new ALendingGenerator(this, count, users, books);
    }


    @Override
    public void show(int id, Boolean shortLongFormat) {
        line();
        get(id).show(true);
    }


    public void showBorrowedBooks() {

        repository.values().stream()
                .filter(x -> !x.getBook().getBorrowed())
                .forEach(x -> {
                    this.line();
                    x.getBook().show(false);
                });
    }


    public void showUsersBorrowedBooks() {

        this.line();

        // uživatelé s půjčenými knihami
        List<Integer> usr = repository.values().stream()
                .map(x -> x.getUser().getId())
                .distinct()
                .toList();

        // které knihy mají půjčené
        usr.forEach(x -> {
            System.out.println(((AUser) users.get(x)).getShortInfo() + " má zapůjčené: ");

            repository.values().stream()
                    .filter(y -> x.equals( y.getUser().getId() ))
                    .forEach(y -> System.out.println(
                                    "|\t" + ((Book) y.getBook()).getShortInfo() + " " +
                                    ((ALoan) y).getShortInfo())
                    );
            this.line();
        });
        System.out.println(" ... konec. ");
    }

}
