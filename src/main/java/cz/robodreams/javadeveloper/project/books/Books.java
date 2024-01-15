package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.util.TerminalColorConst;

import java.util.HashMap;
import java.util.Map;

public class Books implements IBooks, IAddBook {

    private Map<Integer, IBook> dbLibrary = new HashMap<>();

    public void addNewBooks(int countOfNewBooks) {
        new BookDatabase(this, countOfNewBooks);
    }

    public void insertNewBook(int key, IBook value) {
        dbLibrary.put(key, value);
    }

    public IBook getBook(int id) {

        if (id > dbLibrary.size() || id < 0) {
            throw new ArrayIndexOutOfBoundsException("Book: Chybný index.");
        }
        return dbLibrary.get(id);
    }

    public Integer getCountBooks() {
        return dbLibrary.size();
    }


    public void line() {
        System.out.println("+" + "-".repeat(90));
    }

    public void show(int id, Boolean shortLongFormat) {
        line();
        getBook(id).show(true);
    }
}
