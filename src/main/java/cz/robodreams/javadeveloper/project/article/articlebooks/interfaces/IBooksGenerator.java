package cz.robodreams.javadeveloper.project.article.articlebooks.interfaces;

public interface IBooksGenerator {

    String DATABASE_FILE = "db_books";

    Book getBook(int id);

    Integer getCountOfBooks();

}
