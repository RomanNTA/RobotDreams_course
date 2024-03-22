package cz.robodreams.javadeveloper.project.article;


import cz.robodreams.javadeveloper.project.article.articlebooks.LoaderRun;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.*;

import java.util.ArrayList;
import java.util.List;

public class ArticlesRepositoryImpl extends SubjectsImpl<Article> implements ArticlesRepository, Subjects<Article>, SubjectAdd<Article> {


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
    public <T extends Article> T getRandomSubject(ArticleType articleType) {

        try {
            int i = this.<T>getCount(Lock.UNLOCK, articleType);

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
                    .filter(x -> x.getArticleType() == article)
                    .filter(lock -> (locked == Lock.ALL || (lock.getLocked() == locked)))
                    .filter(lock -> (article == ArticleType.ALL || (lock.getArticleType() == article)))
                    .toList();
        } catch (RuntimeException e) {
            return new ArrayList<T>();
        }
    }

    public List<String> getListBook(Lock locked, ArticleType article) {

        System.out.println( "getResultShow Volání ");
        List result = new ArrayList();
        try {
            for (Article art : getList(locked, article)) {

                List tmp = new ArrayList();
                tmp = art.getResultShow(ShowSubjectItems.LONG_FORMAT);
                System.out.println( "getResultShow tmp " + tmp.size() );
                result.addAll(tmp);
            }
        } catch (RuntimeException e) {
            return new ArrayList<>();
        }
        return result;
    }


    public Integer getCount(Lock locked, ArticleType article) {
        return this.<Article>getList(locked, article).size();
    }


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
