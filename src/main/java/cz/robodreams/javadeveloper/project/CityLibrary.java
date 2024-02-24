package cz.robodreams.javadeveloper.project;

import cz.robodreams.javadeveloper.project.books.IBook;
import cz.robodreams.javadeveloper.project.books.IBooks;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.event.EventManager;
import cz.robodreams.javadeveloper.project.event.PaymentManager;
import cz.robodreams.javadeveloper.project.lending.ILending;
import cz.robodreams.javadeveloper.project.lending.ILendingConst;
import cz.robodreams.javadeveloper.project.users.IUsers;

public class CityLibrary implements ICityLibrary, ILendingConst {


    private IBooks books;
    private IUsers users;
    private ILending lending;
    private EventManager eventManager;
    private PaymentManager paymentManager;


    public CityLibrary() {

        this.books = Service.getInstance().getBooks();
        this.users = Service.getInstance().getUser();
        this.lending = Service.getInstance().getLending();
        this.eventManager = Service.getInstance().getEventManager();
        this.paymentManager = Service.getInstance().getPaymentManager();

        /**
         * Načtení 100 knih z databaze.
         */
//        this.books = new Books();
        books.generator(100);

        /**
         * Vygenerování 20 uživatelů knihovny
         */
//        this.usr = new Users();
        users.generator(20);

        /**
         * Vygenerování 20 zapůjčení knih
         */
        lending.generator(20);

    }

    @Override
    public void oneMonth() {

        this.books = Service.getInstance().getBooks();
        this.users = Service.getInstance().getUser();
        this.lending = Service.getInstance().getLending();


        for (int i = 0; i < books.size(); i++) {
            books.show(i, true);
        }

        for (int i = 0; i < users.size(); i++) {
            users.show(i, true);
        }

        books.line();
        System.out.println("Random : " + ((IBook) books.getRandomSubject()).getGenre());
        books.line();

        String genre = books.showBookGenre(true,false);
        System.out.println("žánr: " + genre);
        books.showBooksAccordingToGenre(genre);
        books.line();
        lending.showBorrowedBooks();
        lending.showUsersBorrowedBooks();
        lending.showUsersBorrowedBooks();
        lending.showUsersBorrowedBooks();
        lending.showUsersBorrowedBooks();
        eventManager.generator(0);

//  Vrací stále první pozici z pole. terminal-short-circuiting
//        List<Integer> list = Arrays.asList(2, 4, 6, 8, 10);
//        Optional<Integer> answer = list.stream().findAny();
//        System.out.println("answer : " + answer.get());

    }

    public void oneDay() {

    }



}
