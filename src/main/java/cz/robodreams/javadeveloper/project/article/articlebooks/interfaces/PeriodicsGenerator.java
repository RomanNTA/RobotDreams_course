package cz.robodreams.javadeveloper.project.article.articlebooks.interfaces;

import cz.robodreams.javadeveloper.project.article.interfaces.Article;

public interface PeriodicsGenerator {

    int NAME = 0;
    int PUBLISHER = 1;
    int YEAR_OF_FOUNDATION = 2;
    int PERIODICITY = 3;

    String DATABASE_FILE_BOOKS = "db_books";
    String DATABASE_FILE_NEWS = "db_news";
    String DATABASE_FILE_MAGAZINES = "db_magazines";

//    Article getBook(int id);
//
//    Integer getCount();

}
