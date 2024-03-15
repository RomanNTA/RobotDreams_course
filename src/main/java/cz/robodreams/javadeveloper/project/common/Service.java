package cz.robodreams.javadeveloper.project.common;


import cz.robodreams.javadeveloper.project.article.ArticlesRepositoryImpl;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.lending.ALending;
import cz.robodreams.javadeveloper.project.lending.Lending;
import cz.robodreams.javadeveloper.project.users.Users;
import cz.robodreams.javadeveloper.project.users.UsersImpl;

public class Service {

    private ArticlesRepository article;
    private Users user;
    private Lending lending;
//    private PaymentManager paymentManager;
//    private EventManager eventManager;


    private static Service instance;


    private Service() {
    }

    public static Service getInstance() {

        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public ArticlesRepository getArticle() {

        if (article == null){
            this.article = new ArticlesRepositoryImpl();
        }
        return this.article;
    }

    public Users getUser() {
        if (user == null){
            this.user = new UsersImpl();
        }
        return this.user;
    }

    public Lending getLending() {
        if (lending == null){
            this.lending = new ALending();
        }
        return this.lending;
    }

//    public PaymentManager getPaymentManager() {
//        if (paymentManager == null){
//            this.paymentManager = new PaymentManager();
//        }
//        return this.paymentManager;
//    }
//
//    public EventManager getEventManager() {
//        if (eventManager == null){
//            this.eventManager = new EventManager();
//        }
//        return this.eventManager;
//    }

}
