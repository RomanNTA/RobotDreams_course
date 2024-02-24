package cz.robodreams.javadeveloper.project.common;

import cz.robodreams.javadeveloper.project.books.Books;
import cz.robodreams.javadeveloper.project.books.IBooks;
import cz.robodreams.javadeveloper.project.event.EventManager;
import cz.robodreams.javadeveloper.project.event.PaymentManager;
import cz.robodreams.javadeveloper.project.lending.ALending;
import cz.robodreams.javadeveloper.project.lending.ILending;
import cz.robodreams.javadeveloper.project.users.IUsers;
import cz.robodreams.javadeveloper.project.users.Users;

public class Service {

    private IBooks books;
    private IUsers user;
    private ILending lending;
    private PaymentManager paymentManager;
    private EventManager eventManager;


    private static Service instance;


    private Service() {
    }

    public static Service getInstance() {

        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public IBooks getBooks() {

        if (books == null){
            this.books = new Books();
        }
        return this.books;
    }

    public IUsers getUser() {
        if (user == null){
            this.user = new Users();
        }
        return this.user;
    }

    public ILending getLending() {
        if (lending == null){
            this.lending = new ALending();
        }
        return this.lending;
    }

    public PaymentManager getPaymentManager() {
        if (paymentManager == null){
            this.paymentManager = new PaymentManager();
        }
        return this.paymentManager;
    }

    public EventManager getEventManager() {
        if (eventManager == null){
            this.eventManager = new EventManager();
        }
        return this.eventManager;
    }

}
