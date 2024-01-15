package cz.robodreams.javadeveloper.project.books;

public interface IBooks {

    void addNewBooks(int countOfNewBooks);

    void insertNewBook(int key, IBook value);

    IBook getBook(int id);

    Integer getCountBooks();

    void show(int id, Boolean shortLongFormat);

}
