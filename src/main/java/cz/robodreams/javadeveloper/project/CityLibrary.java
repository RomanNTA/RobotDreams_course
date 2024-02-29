package cz.robodreams.javadeveloper.project;

import cz.robodreams.javadeveloper.project.books.IBook;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.lending.ILendingConst;

public class CityLibrary implements ICityLibrary, ILendingConst {


    Service service = Service.getInstance();

    public CityLibrary() {

        /**
         * Načtení 100 knih z databaze.
         */
        service.getBooks().generator(100);

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

        for (int i = 0; i < service.getBooks().size(); i++) {
            service.getBooks().show(i, true);
        }

        for (int i = 0; i < service.getUser().size(); i++) {
            service.getUser().show(i, true);
        }


//  Vrací stále první pozici z pole. terminal-short-circuiting
//        List<Integer> list = Arrays.asList(2, 4, 6, 8, 10);
//        Optional<Integer> answer = list.stream().findAny();
//        System.out.println("answer : " + answer.get());

    }

    public void oneDay() {



        service.getBooks().line();
        System.out.println("Random : " + ((IBook) service.getBooks().getRandomSubject()).getGenre());
        service.getBooks().line();

        String genre = service.getBooks().showBookGenre(true,false);
        System.out.println("žánr: " + genre);
        service.getBooks().showBooksAccordingToGenre(genre);
        service.getBooks().line();
        service.getLending().showBorrowedBooks();
        service.getLending().showUsersBorrowedBooks();
        service.getEventManager().generator(0);



    }



}
