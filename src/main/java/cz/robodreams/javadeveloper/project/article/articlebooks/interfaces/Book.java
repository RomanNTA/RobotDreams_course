package cz.robodreams.javadeveloper.project.article.articlebooks.interfaces;

import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.lending.ALoan;

public interface Book extends Article {



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


    void show(ShowSubjectItems showItems);

    String getShortInfo();

    String getShortInfoBuying();

    Boolean getBorrowed();

    void setBorrowed(Boolean borrowed, ALoan borrowedReference);

    Integer getIdArticle();

    String getTitle();

    ALoan getBorrowedReference();


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
