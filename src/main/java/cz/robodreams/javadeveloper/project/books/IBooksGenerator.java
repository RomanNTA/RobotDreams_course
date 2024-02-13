package cz.robodreams.javadeveloper.project.books;

public interface IBooksGenerator {

    String DATABASE_FILE = "db_books";
    int DATABASE_FILE_SIZE = 870;

    IItem getBook(int id);
    Integer getCountOfBooks();

}
