package cz.robodreams.javadeveloper.project.books;

public interface IBooksShopping extends IArticle<IItem> {
    Integer buyBooks(int money);

}
