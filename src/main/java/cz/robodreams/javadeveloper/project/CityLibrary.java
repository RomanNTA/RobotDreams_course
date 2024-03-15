package cz.robodreams.javadeveloper.project;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.lending.ILendingConst;

public class CityLibrary implements ICityLibrary, ILendingConst {

    Service service = Service.getInstance();

    public CityLibrary() {

        /**
         * Načtení 100 knih z databaze.
         */
        service.getArticle().loadBooks(100);

        /**
         * Vygenerování 20 uživatelů knihovny
         */
        service.getUser().generator(20);

        /**
         * Vygenerování 20 zapůjčení knih
         */
        service.getLending().generator(20);

    }

    @Override
    public void oneMonth() {

        for (int i = 0; i < service.getArticle().size(); i++) {
            service.getArticle().show(i, ShowSubjectItems.LONG_FORMAT);
        }

        for (int i = 0; i < service.getUser().size(); i++) {
            service.getUser().show(i, ShowSubjectItems.LONG_FORMAT);
        }


//  Vrací stále první pozici z pole. terminal-short-circuiting
//        List<Integer> list = Arrays.asList(2, 4, 6, 8, 10);
//        Optional<Integer> answer = list.stream().findAny();
//        System.out.println("answer : " + answer.get());

    }

    public void oneDay() {

        service.getArticle().line();
        System.out.println("Random : " + ((Book) service.getArticle().getRandomSubject()).getGenre());
        service.getArticle().line();

//        String genre = service.getArticle().showBookGenre(true,false);
//        System.out.println("žánr: " + genre);
//        service.getArticle().showBooksAccordingToGenre(genre);
//
//        service.getArticle().line();
//        service.getLending().showBorrowedBooks();
//        service.getLending().showUsersBorrowedBooks();
//        service.getEventManager().generator(0);

    }



}
