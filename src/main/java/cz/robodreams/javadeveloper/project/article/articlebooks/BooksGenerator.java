package cz.robodreams.javadeveloper.project.article.articlebooks;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.IBooksGenerator;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;
import cz.robodreams.javadeveloper.project.db.ATable;
import cz.robodreams.javadeveloper.project.db.ITable;

public class BooksGenerator implements IBooksGenerator {

    private static Integer counter = 0;
    private ITable allBooks;

    public BooksGenerator(SubjectAdd<Book> destination, int countOfNewBooks) {

        if (destination == null) return;

        this.allBooks = new ATable(DATABASE_FILE);

        if (countOfNewBooks > 0) {
            for (int i = 0; i < countOfNewBooks; i++) {
                destination.add((getBook(counter++)));
            }
        }

        // nákup knih podle ceny. Ukončí až odmítnutí knihy
        if (countOfNewBooks < 0) {
            while (destination.add((getBook(counter)))) {
                counter++;
            }
        }
    }

    public Book getBook(int id) {

        return BookImpl.builder()
                .articleType(ArticleType.BOOKS)
                .idArticle(id)
                .title(allBooks.getString(id, Book.TITLE))
                .author(allBooks.getString(id, Book.AUTHOR))
                .numberOfPages(allBooks.getInteger(id, Book.NUMBEROFPAGES))
                .price(allBooks.getInteger(id, Book.PRICE))
                .isbn(allBooks.getString(id, Book.ISBN))
                .ean(allBooks.getString(id, Book.EAN))
                .custody(allBooks.getString(id, Book.CUSTODY))
                .genre(allBooks.getString(id, Book.GENRE))
                .publisher(allBooks.getString(id, Book.PUBLISHER))
                .profit(allBooks.getInteger(id, Book.PROFIT))
                .borrowed(false)
                .borrowedReference(null)
                .build();
    }

    public Integer getCountOfBooks() {
        return allBooks.getRowsCount(1);
    }

}
