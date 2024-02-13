package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.lending.ALoan;

import static cz.robodreams.javadeveloper.project.books.TypeArticle.BOOK;

public interface IItemBook {

    TypeArticle typeArticle = BOOK;

    String getShortInfoBuying();


    Integer getIdBook();

    String getTitle();

    String getAuthor();

    Integer getNumberOfPages();

    Integer getPrice();

    String getIsbn();

    String getEan();

    String getCustody();

    String getGenre();

    String getPublisher();

    Integer getProfit();


}
