package cz.robodreams.javadeveloper.project.books;

public interface IBookDatabase {

    String DATABASE_FILE = "db_books";

    IBook getBook(int id);
    Integer getCountOfBooks();

}
