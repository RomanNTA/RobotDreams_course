package cz.robodreams.javadeveloper.project.article.interfaces;

import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;

public interface Article {


    ArticleType getArticleType();

    Integer getId();

    void show(ShowSubjectItems showItems);

}
