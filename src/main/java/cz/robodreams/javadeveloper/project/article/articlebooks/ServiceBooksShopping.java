package cz.robodreams.javadeveloper.project.article.articlebooks;


import cz.robodreams.javadeveloper.project.article.ArticleImpl;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;

public class ServiceBooksShopping implements SubjectAdd<Book> {

    String MESSAGE_BUY_COMPLETED = "\t... nákup knihy v ceně %d Kč. Zbývá %d Kč";

    private Integer money;
    private ArticlesRepository articlesRepository;

    public ServiceBooksShopping(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }


    public Integer buyBooks(int money) {
        this.money = money;
        new BooksGenerator(this, -1);
        // kolik stály knihy
        return money - this.money;
    }

    @Override
    public Boolean add(Book value) {

        if (value.getPrice() <= money) {
            //line();
            articlesRepository.add(value);
            money -= value.getPrice();
            System.out.println(value.getShortInfoBuying());
            System.out.println(String.format(MESSAGE_BUY_COMPLETED, value.getPrice(), money));
            return true;
        }
        // nejsou money ... konec
        return false;
    }

}