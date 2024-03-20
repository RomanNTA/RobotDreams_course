package cz.robodreams.javadeveloper.project.common;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;

public interface Subjects<T> {

    ArticleType getArticleType();

    Integer getId();
    T get(int id);
    Boolean add(T value);

    Integer size();

    void generator(int count);
    T getRandomSubject();

    void show(int id, ShowSubjectItems showItems);



}
