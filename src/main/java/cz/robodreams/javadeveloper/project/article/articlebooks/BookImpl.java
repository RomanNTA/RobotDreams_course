package cz.robodreams.javadeveloper.project.article.articlebooks;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.lending.ALoan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

@Builder
@Getter
@Setter
public class BookImpl implements Book {

    private Lock locked;
    private Integer idArticle;
    private Boolean borrowed = false;
    private ALoan borrowedReference;


    private ArticleType articleType;
    private String title;
    private String author;
    private Integer numberOfPages;
    private Integer price; // cena knihy
    private String isbn;
    private String ean;
    private String custody;
    private String genre;
    private String publisher;
    private Integer profit;  // cena za vypůjčení knihy


    @Override
    public void show(ShowSubjectItems showItems) {
        System.out.println(getResultShow(showItems));
    }

    @Override
    public List<String> getResultShow(ShowSubjectItems showItems) {

        BiPredicate<ShowSubjectItems, ShowSubjectItems> testShowItems = (x, y) -> (x != null && x.equals(y));

        List<String> result = new ArrayList();
        result.add(Util.getLine());

        result.add(String.format("| Název: " + Util.colCyan("%40s"), title) +
                String.format("  Nakladatelství : " + Util.colCyan("%15s"), publisher));

        result.add(String.format("| Autor: " + Util.colCyan("%40s"), author) +
                String.format("  Počet stran : " + Util.colCyan("%18s"), numberOfPages));

        if (testShowItems.test(showItems, ShowSubjectItems.SHORT_FORMAT)) {
            return result;
        }

        result.add(String.format("| Obor:  " + Util.colCyan("%40s"), genre) +
                String.format("  Vazba: " + Util.colCyan("%25s"), custody));

        result.add(String.format("| ISBN: %s. EAN: %s. Poplatek: " + Util.colRed("%d") + " Kč. Cena : " + Util.colRed("%d") + " Kč.",
                isbn, ean, profit, price));

        if (getBorrowed()) {
            result.addAll(borrowedReference.getResultShow(showItems));
        } else {
            result.add(String.format("| Kniha je nyní : %s.", Util.colGreen("k dispozici")));
        }
        return result;
    }


    @Override
    public String getShortInfo() {

        return String.format("  Název: " + Util.colCyan("%-25s"), title) +
                String.format(" Autor: " + Util.colCyan("%-20s"), author);
    }

    public String getShortInfoBuying() {
        return String.format(String.format(Util.colCyan("%-40s") + Util.colRed("%d Kč."), title, price));
    }


    public void setBorrowed(Boolean borrowed, ALoan borrowedReference) {
        this.borrowed = borrowed;
        this.borrowedReference = borrowed ? borrowedReference : null;
    }


}
