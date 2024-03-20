package cz.robodreams.javadeveloper.project.article.articlebooks.interfaces;

import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.lending.ALoan;

public interface Book extends Article {


    void setLocked(Lock locked);

    void setIdArticle(Integer article);

    void setBorrowed(Boolean borrowed);

    void setBorrowedReference(ALoan aLoan);


    String getShortInfoBuying();

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
