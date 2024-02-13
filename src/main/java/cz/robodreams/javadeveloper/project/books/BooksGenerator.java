package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.common.ISubjectAdd;
import cz.robodreams.javadeveloper.project.db.ATable;
import cz.robodreams.javadeveloper.project.db.ITable;

public class BooksGenerator implements IBooksGenerator {

    private static Integer counter = 0;
    private ITable allBooks;

    public BooksGenerator(ISubjectAdd<IItem> destination, int countOfNewBooks) {

        if (destination == null) return;
        if (counter >= DATABASE_FILE_SIZE) return;

        this.allBooks = new ATable(DATABASE_FILE);

        if (countOfNewBooks > 0 ) {
            for (int i = 0; i < countOfNewBooks; i++) {
                if (counter >= DATABASE_FILE_SIZE) return;
                destination.add(counter, (getBook(counter)));
                counter++;
            }
        }

        // nákup knih podle ceny. Ukončí až odmítnutí knihy
        if (countOfNewBooks < 0 ) {
            while (destination.add(counter, (getBook(counter)))) {
                if (counter >= DATABASE_FILE_SIZE) return;
                counter++;
            }
        }

    }

    public IItem getBook(int id) {

        return new AItemBook(id, allBooks.getString(id, 0), allBooks.getString(id, 1),
                allBooks.getInteger(id, 2), allBooks.getInteger(id, 3), allBooks.getString(id, 4),
                allBooks.getString(id, 5), allBooks.getString(id, 6), allBooks.getString(id, 7),
                allBooks.getString(id, 8), allBooks.getInteger(id, 9));
    }

    public Integer getCountOfBooks() {
        return allBooks.getRowsCount(1);
    }

}
