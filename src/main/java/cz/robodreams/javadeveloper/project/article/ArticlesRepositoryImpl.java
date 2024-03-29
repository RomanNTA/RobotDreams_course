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


    public void loadArticle() {
        new LoaderRun(this).run();
    }

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


//    @Override
//    public Article getRandomSubject(ArticleType articleType) {
//        try {
//            //return (repository.values().stream()
//            return (repository.stream()
//                    .filter(x -> (x.getArticleType() == articleType) )
//                    .skip(Util.getRandomId(0,repository.size()-1))
//                    .findAny()
//            ).get();
//        } catch (NullPointerException e) {
//            return null;
//        }
//    }

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

    public Integer getCount(Lock locked, ArticleType article) {
        return this.<Article>getList(locked, article).size();
    }


    public Map<String, Long> getListBooksAccordingGenre(Lock locked) {

        return repository.stream()
                .filter(x -> x.getArticleType() == ArticleType.BOOKS)
                .filter(x -> testLocked.test(locked, x.getLocked()))
                .filter(x -> !((Book) x).getGenre().isEmpty())
                .map(x -> ((Book) x).getGenre())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }


//    public void showBooksAccordingToGenre(String genre) {
//
//        List<Integer> listOfBooks = repository.stream()
//                .filter(x -> !x.getBorrowed())
//                .filter(p -> p.getGenre() == genre)
//                .map(x -> x.getIdArticle())
//                .toList();
//
//        System.out.printf("Seznam voných knih v knihovně pro žánr '%s'.\n", genre);
//        for (int i = 0; i < listOfBooks.size(); i++) {
//            this.show(listOfBooks.get(i), true);
//        }
//    }

    /**
     * @return Map<idArticle, Lines>
     */

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


//    public List<String> getListBook(Lock locked, ArticleType article) {
//
//        List result = new ArrayList();
//        //System.out.println( "getResultShow Volání " + result.size() );
//        try {
//            for (Article art : this.<Book>getList(locked, article)) {
////                List tmp = new ArrayList();
////                tmp = art.getResultShow(ShowSubjectItems.LONG_FORMAT);
////                System.out.println( "getResultShow tmp " + tmp.size() );
////                result.addAll(tmp);
//                result.addAll(art.getResultShow(ShowSubjectItems.LONG_FORMAT));
//            }
//        } catch (RuntimeException e) {
//            return new ArrayList<>();
//        }
//        //System.out.println( "getResultShow vrací celkem " + result.size());
//        return result;
//    }


//
//
//    public String showBookGenre(Boolean returnRandomGenre, Boolean showAllGenre) {
//
//        //Map<String, Long> booksGenre = repository.values().stream()
//        Map<String, Long> booksGenre = repository.stream()
//                .filter(x -> x.getArticleType() == ArticleType.BOOKS)
//                .map(x -> x.getArticleType() == ArticleType.BOOKS)
//                .filter(x -> x.getGenre().length() > 0)
//                .filter(x -> !x.getBorrowed())
//                .map(x -> x.getGenre())
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//
//        if (showAllGenre) {
//            System.out.println("Seznam žánrů v knihovně.");
//            System.out.println("=".repeat(30));
//            booksGenre.entrySet().stream().forEach(x -> System.out.println(x.getKey()));
//        }
//
//        String result = "";
//        try {
//            if (returnRandomGenre) {
//                result = (booksGenre.keySet().stream()
//                        .skip(Util.getRandomId(0,booksGenre.size()-1))
//                        .findAny()
//                ).get();
//            }
//        } catch (NullPointerException e) {
//            return "";
//        }
//        return result;
//    }
//
//    public void showBooksAccordingToGenre(String genre) {
//
//        List<Integer> listOfBooks = repository.stream()
//                .filter(x -> !x.getBorrowed())
//                .filter(p -> p.getGenre() == genre)
//                .map(x -> x.getIdArticle())
//                .toList();
//
//        System.out.printf("Seznam voných knih v knihovně pro žánr '%s'.\n", genre);
//        for (int i = 0; i < listOfBooks.size(); i++) {
//            this.show(listOfBooks.get(i), true);
//        }
//    }


}
