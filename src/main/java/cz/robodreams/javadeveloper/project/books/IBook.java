package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.lending.ALoan;

public interface IBook {


    void show(Boolean shortLongFormat);

    String getShortInfo();

    String getShortInfoBuying();

    Boolean getBorrowed();

    void setBorrowed(Boolean borrowed, ALoan borrowedReference);

    ALoan getBorrowedReference();


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
