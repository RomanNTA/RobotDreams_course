package cz.robodreams.javadeveloper.project.article;

import cz.robodreams.javadeveloper.project.article.articlebooks.ServiceBookGenerator;
import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.SubjectsImpl;

public class ArticlesRepositoryImpl extends SubjectsImpl<Article> implements ArticlesRepository {

    public void loadBooks(Integer count) {

        ServiceBookGenerator serviceBookGenerator = new ServiceBookGenerator(this);
        serviceBookGenerator.generator(count);
    }

    public void loadMagazines(Integer count) {

//        ServiceBookGenerator serviceBookGenerator = new ServiceBookGenerator(this);
//        serviceBookGenerator.generator(count);
    }

    public void loadNews(Integer count) {

//        ServiceBookGenerator serviceBookGenerator = new ServiceBookGenerator(this);
//        serviceBookGenerator.generator(count);
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
        line();
        get(id).show(showItems);
    }


//    @Override
//    public IBook getRandomSubject() {
//        try {
//            //return (repository.values().stream()
//            return (repository.stream()
//                    .skip(Util.getRandomId(0,repository.size()-1))
//                    .findAny()
//            ).get();
//        } catch (NullPointerException e) {
//            return null;
//        }
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
