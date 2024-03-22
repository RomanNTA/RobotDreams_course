package cz.robodreams.javadeveloper.project;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.*;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.server.ServerImpl;
import cz.robodreams.javadeveloper.project.lending.ILendingConst;

import java.util.List;

public class CityLibrary {

    Service service = Service.getInstance();

    public CityLibrary() {

        /**
         * Načtení 100 knih z databaze.
         */
        service.getArticle().loadArticle();

        /**
         * Vygenerování 20 uživatelů knihovny
         */
        service.getUser().generator(20);

        /**
         * Vygenerování 20 zapůjčení knih
         */
        service.getLending().generator(20);

    }

    public void run() {

        new ServerImpl().start();




//        new ControlStrategy();
//
//        for (int i = 0; i < service.getArticle().size(); i++) {
//            service.getArticle().show(i, ShowSubjectItems.LONG_FORMAT);
//        }
//
//        for (int i = 0; i < service.getUser().size(); i++) {
//            service.getUser().show(i, ShowSubjectItems.LONG_FORMAT);
//        }
//
//
////  Vrací stále první pozici z pole. terminal-short-circuiting
////        List<Integer> list = Arrays.asList(2, 4, 6, 8, 10);
////        Optional<Integer> answer = list.stream().findAny();
////        System.out.println("answer : " + answer.get());
//
//
//        oneDay();

    }

    public void oneDay() {

//        Util.line();
//
//        System.out.println("Random : " );
//        Util.line();
//        service.getArticle().<Book>getRandomSubject(ArticleType.BOOKS).show(ShowSubjectItems.LONG_FORMAT);
//        Util.line();
//        service.getArticle().<Periodic>getRandomSubject(ArticleType.NEWS).show(ShowSubjectItems.LONG_FORMAT);
//        Util.line();
//        service.getArticle().<Periodic>getRandomSubject(ArticleType.MAGAZINES).show(ShowSubjectItems.LONG_FORMAT);
//        Util.line();
//
//
//        Util.line();
//        System.out.println("Locked : " );
//        service.getArticle().<Book>getRandomSubject(ArticleType.BOOKS).setLocked(Lock.UNLOCK);
//
//        List<Book> list = service.getArticle().getList(Lock.UNLOCK, ArticleType.BOOKS);
//        list.forEach(x -> x.show(ShowSubjectItems.LONG_FORMAT));
//
//
////
////
////        String genre = service.getArticle().showBookGenre(true,false);
////        System.out.println("žánr: " + genre);
////        service.getArticle().showBooksAccordingToGenre(genre);
////
////        service.getArticle().line();
////        service.getLending().showBorrowedBooks();
////        service.getLending().showUsersBorrowedBooks();
//
//
////        service.getEventManager().generator(0);

    }



}
