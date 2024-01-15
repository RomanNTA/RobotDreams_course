package cz.robodreams.javadeveloper.project;

import cz.robodreams.javadeveloper.project.books.Books;
import cz.robodreams.javadeveloper.project.books.IBooks;
import cz.robodreams.javadeveloper.project.users.IUsers;
import cz.robodreams.javadeveloper.project.users.Users;

public class CityLibrary implements ICityLibrary {

    public CityLibrary() {

        /**
         * Načtení 30 knih z databaze.
         */
        IBooks books = new Books();
        books.addNewBooks(100);

        for (int i = 0; i < books.getCountBooks(); i++) {
            books.show(i,true);
        }

        /**
         * Vygenerování 10 uživatelů knihovny
         */
        IUsers usr = new Users();
        usr.addNewUsers(20);

        for (int i = 0; i < usr.getCountPerson(); i++) {
            usr.show(i,true);
        }


    }

    @Override
    public void oneMonth() {
    }


}
