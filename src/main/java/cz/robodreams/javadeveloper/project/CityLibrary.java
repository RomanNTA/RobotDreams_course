package cz.robodreams.javadeveloper.project;

import cz.robodreams.javadeveloper.project.books.Article;
import cz.robodreams.javadeveloper.project.books.IArticle;

import cz.robodreams.javadeveloper.project.books.IItemBook;
import cz.robodreams.javadeveloper.project.event.EventManager;
import cz.robodreams.javadeveloper.project.lending.ALending;
import cz.robodreams.javadeveloper.project.lending.ILending;
import cz.robodreams.javadeveloper.project.lending.ILendingConst;
import cz.robodreams.javadeveloper.project.users.IUsers;
import cz.robodreams.javadeveloper.project.users.Users;

public class CityLibrary implements ICityLibrary, ILendingConst {


    private IArticle<IItemBook> books;
    private IUsers usr;
    private ILending lending;


    public CityLibrary() {

        /**
         * Načtení 100 knih z databaze.
         */
        this.books = new Article();
        //books.generator(100);


        /**
         * Vygenerování 20 uživatelů knihovny
         */
        this.usr = new Users();
        //usr.generator(20);


        /**
         * Vygenerování 20 zapůjčení knih
         */
        this.lending = new ALending(usr, books);
        //lending.generator(20);
//        lending.show(1,false);;





    }

    @Override
    public void oneMonth() {

//        for (int i = 0; i < books.size(); i++) {
//            books.show(i, true);
//        }
//
//        for (int i = 0; i < usr.size(); i++) {
//            usr.show(i, true);
//        }
//
//        books.line();
//        System.out.println("Random : " + ((IBook) books.getRandomSubject()).getGenre());
//        books.line();
//
//        String genre = books.showBookGenre(true,false);
//        System.out.println("žánr: " + genre);
//        books.showBooksAccordingToGenre(genre);
//        books.line();
//        lending.showBorrowedBooks();
//        lending.showUsersBorrowedBooks();


        EventManager ev = new EventManager(books, usr,lending);
        ev.generator(0);



//  Vrací stále první pozici z pole. terminal-short-circuiting
//        List<Integer> list = Arrays.asList(2, 4, 6, 8, 10);
//        Optional<Integer> answer = list.stream().findAny();
//        System.out.println("answer : " + answer.get());

    }

    public void oneDay() {

    }



}
