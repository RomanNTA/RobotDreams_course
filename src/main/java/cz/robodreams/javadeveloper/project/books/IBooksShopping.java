package cz.robodreams.javadeveloper.project.books;

public interface IBooksShopping extends IBooks<IBook> {
    Integer buyBooks(int money);
    
}
