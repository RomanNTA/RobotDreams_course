package cz.robodreams.javadeveloper.project.books;

public interface IBooksGenerator {

    String DATABASE_FILE = "db_books";

    IBook getBook(int id);
    Integer getCountOfBooks();

}
