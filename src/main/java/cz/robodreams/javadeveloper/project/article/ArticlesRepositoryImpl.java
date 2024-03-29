package cz.robodreams.javadeveloper.project.article;


import cz.robodreams.javadeveloper.project.article.articlebooks.LoaderRun;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArticlesRepositoryImpl extends SubjectsImpl<Article> implements ArticlesRepository, Subjects<Article>, SubjectAdd<Article> {

    BiPredicate<Lock, Lock> testLocked = (needLock, bookLock) -> (needLock.equals(Lock.ALL) || (bookLock.equals(needLock)));
    BiPredicate<ArticleType, ArticleType> testArticle = (needArticle, thisArticle) -> (needArticle.equals(ArticleType.ALL) || (thisArticle.equals(needArticle)));
    BiPredicate<String, String> testGenre = (needGenre, thisGenre) -> (needGenre.equals("") || (thisGenre.equals(needGenre)));

    @Override
    public ArticleType getArticleType() {
        return null;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void generator(int count) {

    }

    @Override
    public void show(int id, ShowSubjectItems showItems) {
        Util.line();
        get(id).show(showItems);
    }

    @Override
    public <T extends Article> T getRandomSubject(Lock locked, ArticleType articleType) {

        try {
            int i = this.<T>getCount(locked, articleType);

            return (T) (repository.stream()
                    .filter(x -> (x.getArticleType() == articleType))
                    .map(x -> ((T) x))
                    .skip(Util.getRandomId(0, i - 1))
                    .findAny()
            ).get();

        } catch (RuntimeException e) {
            return null;
        }
    }

    @Override
    public <T extends Article> List<T> getList(Lock locked, ArticleType article) {

        try {
            return repository.stream()
                    .map(x -> ((T) x))
                    .filter(items -> testArticle.test(article, items.getArticleType()))
                    .filter(items -> testLocked.test(locked, items.getLocked()))
                    .toList();

        } catch (RuntimeException e) {
            return new ArrayList<T>();
        }
    }


    @Override
    public List<String> getListBook(Lock locked, ArticleType article) {
        try {
            return this.<Book>getList(locked, article).stream()
                    .map(x -> x.getResultShow(ShowSubjectItems.LONG_FORMAT))
                    .flatMap(Collection::stream)
                    .toList();
        } catch (RuntimeException e) {
            return new ArrayList<>();
        }
    }


    @Override
    public List<String> getListBorrowedBook() {

        try {
            return repository.stream()
                    .filter(items -> items.getArticleType() == ArticleType.BOOKS)
                    .filter(items -> items.getLocked() == Lock.UNLOCK)
                    .filter(Article::getBorrowed)
                    .map(items -> items.getResultShow(ShowSubjectItems.LONG_FORMAT))
                    .flatMap(Collection::stream)
                    .toList();

        } catch (RuntimeException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Integer getCount(Lock locked, ArticleType article) {
        return this.<Article>getList(locked, article).size();
    }


    @Override
    public Map<String, Long> getListBooksAccordingGenre(Lock locked) {

        return repository.stream()
                .filter(x -> x.getArticleType() == ArticleType.BOOKS)
                .filter(x -> testLocked.test(locked, x.getLocked()))
                .filter(x -> !((Book) x).getGenre().isEmpty())
                .map(x -> ((Book) x).getGenre())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    @Override
    public Map<Integer, Book> showBooksAccordingToGenreForBuyin(String genre) {

        try {
            AtomicInteger counter = new AtomicInteger(1);
            return this.<Book>getList(Lock.LOCK, ArticleType.BOOKS).stream()
                    .filter(items -> (testGenre.test(genre, items.getGenre())))
                    .collect(Collectors.toMap(x -> (counter.getAndIncrement()), Function.identity()));

        } catch (RuntimeException e) {
            return new HashMap<>();
        }
    }

    @Override
    public Map<Integer,Book> showFreeBooks(ShowSubjectItems showSubjectItems) {

        try {
            AtomicInteger counter = new AtomicInteger(1);
            return repository.stream()
                    .filter(items -> items.getArticleType() == ArticleType.BOOKS)
                    .filter(items -> items.getLocked() == Lock.UNLOCK)
                    .filter(items -> !items.getBorrowed())
                    .map(items -> ((Book) items))
                    .collect(Collectors.toMap(x -> (counter.getAndIncrement()), Function.identity()));
        } catch (RuntimeException e) {
            return new HashMap<>();
        }
    }


    @Override
    public void loadArticle() {
        new LoaderRun(this).run();
    }

    @Override
    public void unlockAnyBooks(int count) {

        int counter = 0;
        while (counter < repository.size() && counter < count) {

            Article a = repository.get(counter);
            if (a.getArticleType() == ArticleType.BOOKS && a.getLocked() == Lock.LOCK) {
                ((Book) a).setLocked(Lock.UNLOCK);
                counter++;
            }
        }
    }


}
