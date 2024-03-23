package cz.robodreams.javadeveloper.project.article;


import cz.robodreams.javadeveloper.project.article.articlebooks.LoaderRun;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArticlesRepositoryImpl extends SubjectsImpl<Article> implements ArticlesRepository, Subjects<Article>, SubjectAdd<Article> {

    BiPredicate<Lock,Lock> testLocked = (needLock, bookLock) -> (needLock.equals(Lock.ALL) || (bookLock.equals(needLock)));
    BiPredicate<ArticleType,ArticleType> testArticle = (needArticle, thisArticle) -> (needArticle.equals(ArticleType.ALL) || (thisArticle.equals(needArticle)));
    BiPredicate<String,String> testGenre = (needGenre, thisGenre) -> (needGenre.equals("") || (thisGenre.equals(needGenre)));


    public void loadArticle() {
        new LoaderRun(this).run();
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

                    //.filter(x -> x.getArticleType() == article)
                    .filter(items -> testArticle.test(article,items.getArticleType()))

                    //.filter(lock -> (locked == Lock.ALL || (lock.getLocked() == locked)))
                    .filter(items -> testLocked.test(locked, items.getLocked()))

                    //.filter(items -> testArticle(article, items.getArticleType()))
                    //.filter(lock -> (article == ArticleType.ALL || (lock.getArticleType() == article)))

                    .toList();

        } catch (RuntimeException e) {
            return new ArrayList<T>();
        }
    }

    public List<String> getListBook(Lock locked, ArticleType article) {

        List<String> result = new ArrayList();
        //System.out.println( "getResultShow Volání " + result.size() );
        try {
            for (Article art : this.<Book>getList(locked, article)) {
//                List tmp = new ArrayList();
//                tmp = art.getResultShow(ShowSubjectItems.LONG_FORMAT);
//                System.out.println( "getResultShow tmp " + tmp.size() );
//                result.addAll(tmp);
                result.addAll(art.getResultShow(ShowSubjectItems.LONG_FORMAT));
            }
        } catch (RuntimeException e) {
            return new ArrayList<>();
        }
        //System.out.println( "getResultShow vrací celkem " + result.size());
        return result;
    }









    public Integer getCount(Lock locked, ArticleType article) {
        return this.<Article>getList(locked, article).size();
    }

    public Map<String, Long> getListBooksAccordingGenre(Lock locked) {

        Map<String, Long> booksGenre = repository.stream()
                .filter(x -> x.getArticleType() == ArticleType.BOOKS)
                .filter(x -> testLocked.test(locked, x.getLocked()))
                .filter(x -> !((Book) x).getGenre().isEmpty())
                .map(x -> ((Book) x).getGenre())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

//        System.out.println("Seznam žánrů v knihovně.");
//        System.out.println("=".repeat(30));
//        booksGenre.entrySet().stream().forEach(x -> System.out.println(x.getKey()));
//
        return booksGenre;
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

    public  Map<Book, List<String>> showBooksAccordingToGenreForBuyin(String genre) {

        Map<Book, List<String>> result = new HashMap<>();
        //System.out.println( "getResultShow Volání " + result.size() );
        try {

            List<Book> books = this.<Book>getList(Lock.LOCK, ArticleType.BOOKS)
                    .stream()
                    .filter(items -> testGenre.test(genre, items.getGenre()))
                    .toList();
            int counter = 1;
            List tmp = new ArrayList();
            for (Book book : books) {
                tmp.clear();

                tmp.add(Util.getLine());
                tmp.add(" Kniha č. " +  counter++);
                tmp.addAll(book.getResultShow(ShowSubjectItems.LONG_FORMAT));
                result.put(book, tmp);
            }
            System.out.println( "showBooksAccordingToGenreForBuyin vrací celkem " + result.size());
            return result;

        } catch (RuntimeException e) {
            return new HashMap<>();
        }
        //System.out.println( "getResultShow vrací celkem " + result.size());


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
