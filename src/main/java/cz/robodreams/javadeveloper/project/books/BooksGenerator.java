package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.common.ISubjectAdd;
import cz.robodreams.javadeveloper.project.db.ATable;
import cz.robodreams.javadeveloper.project.db.ITable;

import java.util.Currency;

public class BooksGenerator implements IBooksGenerator {

    private static Integer counter = 0;
    private ITable allBooks;

    public BooksGenerator(ISubjectAdd<IBook> destination, int countOfNewBooks) {

        if (destination == null) return;

        this.allBooks = new ATable(DATABASE_FILE);

        if (countOfNewBooks > 0 ) {
            for (int i = 0; i < countOfNewBooks; i++) {
                destination.add(counter, (getBook(counter)));
                counter++;
            }
        }

        // nákup knih podle ceny. Ukončí až odmítnutí knihy
        if (countOfNewBooks < 0 ) {
            while (destination.add(counter, (getBook(counter)))) {
                counter++;
            }
        }

    }

    public IBook getBook(int id) {

        return new Book(id, allBooks.getString(id, 0), allBooks.getString(id, 1),
                allBooks.getInteger(id, 2), allBooks.getInteger(id, 3), allBooks.getString(id, 4),
                allBooks.getString(id, 5), allBooks.getString(id, 6), allBooks.getString(id, 7),
                allBooks.getString(id, 8), allBooks.getInteger(id, 9));
    }

    public Integer getCountOfBooks() {
        return allBooks.getRowsCount(1);
    }

}
