package cz.robodreams.javadeveloper.project.article.articlemagazines;

import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.lending.ALoan;

public class interfaces extends Article {

    int TYPE = 0;
    int NAME = 1;
    int PUBLISHER = 2;
    int YEAR_OF_FOUNDATION = 3;
    int PERIODICITY = 4;


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

