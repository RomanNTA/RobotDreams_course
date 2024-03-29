package cz.robodreams.javadeveloper.project.article.interfaces;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;

import java.util.List;
import java.util.Map;

public interface ArticlesRepository {

    void loadArticle();

    Boolean add(Article value);

    Integer size();

    void show(int id, ShowSubjectItems showItems);


//    Article getRandomSubject(ArticleType articleType);

    <T extends Article> T getRandomSubject(Lock locked, ArticleType articleType);

    <T extends Article> List<T> getList(Lock locked, ArticleType article);


    Map<String, Long> getListBooksAccordingGenre(Lock locked);

    Map<Integer, Book> showBooksAccordingToGenreForBuyin(String genre);

    List<String> getListBorrowedBook();

    //List<Article> showFreeBooks(ShowSubjectItems showSubjectItems);
    Map<Integer,Book> showFreeBooks(ShowSubjectItems showSubjectItems);

    void unlockAnyBooks(int count);

}
