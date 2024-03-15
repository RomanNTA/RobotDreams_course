package cz.robodreams.javadeveloper.project.common;

import cz.robodreams.javadeveloper.project.article.interfaces.ArticleType;

public interface Subjects<T> {

    ArticleType getArticleType();
    Integer getId();


    Integer size();

    T get(int id);

    Boolean add(T value);


    void show(int id, ShowSubjectItems showItems);



    void generator(int count);

    T getRandomSubject();



    void line();

//    void show(int id, Boolean shortLongFormat);


}
