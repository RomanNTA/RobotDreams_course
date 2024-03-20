package cz.robodreams.javadeveloper.project.article.articlebooks;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.*;
import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;
import cz.robodreams.javadeveloper.project.db.ATable;
import cz.robodreams.javadeveloper.project.db.ITable;

public class GeneratorImpl implements PeriodicsGenerator, BooksGenerator {

    private ITable databaseArticle;
    private SubjectAdd<Article> destination;
    private Integer counter = 0;


    public GeneratorImpl(SubjectAdd<Article> destination) {

        if (destination == null) return;
        this.destination = destination;

        // musí být volán první !
        loadBooks(DATABASE_FILE_BOOKS);

        loadPeriodic(DATABASE_FILE_NEWS, ArticleType.NEWS);
        loadPeriodic(DATABASE_FILE_MAGAZINES, ArticleType.MAGAZINES);
    }

    private void loadPeriodic(String database, ArticleType articleType){
        databaseArticle = new ATable(database);
        int id = 0;
        for (int i = 0; i < databaseArticle.getRowsCount(0); i++) {
            destination.add((getPeriodic(counter++, id++, articleType)));
        }
    }

    private void loadBooks(String database){
        databaseArticle = new ATable(database);
        for (int i = 0; i < databaseArticle.getRowsCount(0); i++) {
            destination.add((getBook(counter++)));
        }
    }


    public Periodic getPeriodic(int idArticle, Integer idDatabase, ArticleType articleType) {

        return PeriodicImpl.builder()
                .articleType(articleType)
                .locked(Lock.LOCK)
                .idArticle(idArticle)
                .title(databaseArticle.getString(idDatabase, PeriodicsGenerator.NAME))
                .price(0)
                .publisher(databaseArticle.getString(idDatabase, PeriodicsGenerator.PUBLISHER))
                .yearOfFoundation(databaseArticle.getInteger(idDatabase, PeriodicsGenerator.YEAR_OF_FOUNDATION))
                .periodicity(databaseArticle.getString(idDatabase, PeriodicsGenerator.PERIODICITY))
                .build();
    }

    public Book getBook(int id) {

        return BookImpl.builder()
                .articleType(ArticleType.BOOKS)
                .locked(Lock.LOCK)
                .idArticle(id)
                .title(databaseArticle.getString(id, BooksGenerator.TITLE))
                .author(databaseArticle.getString(id, BooksGenerator.AUTHOR))
                .numberOfPages(databaseArticle.getInteger(id, BooksGenerator.NUMBEROFPAGES))
                .price(databaseArticle.getInteger(id, BooksGenerator.PRICE))
                .isbn(databaseArticle.getString(id, BooksGenerator.ISBN))
                .ean(databaseArticle.getString(id, BooksGenerator.EAN))
                .custody(databaseArticle.getString(id, BooksGenerator.CUSTODY))
                .genre(databaseArticle.getString(id, BooksGenerator.GENRE))
                .publisher(databaseArticle.getString(id, BooksGenerator.PUBLISHER))
                .profit(databaseArticle.getInteger(id, BooksGenerator.PROFIT))
                .borrowed(false)
                .borrowedReference(null)
                .build();
    }

}
