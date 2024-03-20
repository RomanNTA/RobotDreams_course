package cz.robodreams.javadeveloper.project.article.interfaces;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.lending.ALoan;

public interface Article {

    Lock getLocked();

    Integer getIdArticle();

    ArticleType getArticleType();

    void show(ShowSubjectItems showItems);



    Boolean getBorrowed();

    void setBorrowed(Boolean borrowed);




    String getShortInfo();


    ALoan getBorrowedReference();




}
