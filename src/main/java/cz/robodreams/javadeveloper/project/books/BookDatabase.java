package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.db.ATable;
import cz.robodreams.javadeveloper.project.db.ITable;

public class BookDatabase implements IBookDatabase {

    private static Integer counter = 0;
    private ITable allBooks;

    public BookDatabase(IAddBook destination, int countOfNewBooks) {

        this.allBooks = new ATable(DATABASE_FILE);

        for (int i = 0; i < countOfNewBooks; i++) {
            destination.insertNewBook(counter, (getBook(counter)));
            counter++;
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
