package cz.robodreams.javadeveloper.project.article.interfaces;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.lending.ALoan;

import java.util.List;

public interface Article {

    Lock getLocked();

    Integer getIdArticle();

    ArticleType getArticleType();

    void show(ShowSubjectItems showItems);

    List<String> getResultShow(ShowSubjectItems showItems);

    Boolean getBorrowed();

    void setBorrowed(Boolean borrowed);

    ALoan getBorrowedReference();
}
