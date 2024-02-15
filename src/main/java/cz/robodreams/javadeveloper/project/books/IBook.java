package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.lending.ALoan;

public interface IBook {


    int TITLE = 0;
    int AUTHOR = 1;
    int NUMBEROFPAGES = 2;
    int PRICE = 3;
    int ISBN = 4;
    int EAN = 5;
    int CUSTODY = 6;
    int GENRE = 7;
    int PUBLISHER = 8;
    int PROFIT = 9;


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
