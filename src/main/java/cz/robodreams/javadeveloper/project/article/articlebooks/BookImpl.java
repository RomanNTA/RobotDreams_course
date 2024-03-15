package cz.robodreams.javadeveloper.project.article.articlebooks;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.lending.ALoan;
import cz.robodreams.javadeveloper.project.common.Util;
import lombok.Builder;
import lombok.Getter;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Builder
@Getter
public class BookImpl implements Book {

    private ArticleType articleType;
    private Integer idArticle;
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
    private Boolean borrowed = false;

    private ALoan borrowedReference;




    /**
     * @param shortLongFormat ... true = dlouhé
     */
    public void show(ShowSubjectItems showItems) {

        BiPredicate<ShowSubjectItems,ShowSubjectItems> testShowItems =(x , y) -> ( x == y);
        boolean view = testShowItems.test(showItems,ShowSubjectItems.LONG_FORMAT);

        String s = "";
        s += String.format("| Název: " + Util.colCyan("%40s"), title);
        s += String.format("  Nakladatelství : " + Util.colCyan("%15s"), publisher);

        if ( view) {

            s += String.format("\n| Autor: " + Util.colCyan("%40s"), author);
            s += String.format("  Počet stran : " + Util.colCyan("%18s \r\n"), numberOfPages);

            s += String.format("| Obor:  " + Util.colCyan("%40s"), genre);
            s += String.format("  Vazba: " + Util.colCyan("%25s \r\n"), custody);

            s +=  view ? String.format("| ISBN: %s. EAN: %s. Poplatek: " + Util.colRed("%d") + " Kč. Cena : " + Util.colRed("%d") + " Kč.",
                    isbn, ean, profit, price) : "";
        }

        if (!getBorrowed()) {
            s += String.format("\r\n| Kniha je nyní : %s.", Util.colGreen("k dispozici"));
        }

        System.out.println(s);

        if (getBorrowed()) {

            borrowedReference.show(showItems);
        }

    }

    public String getShortInfo(){
        return String.format(String.format(Util.colCyan("%-40s"), title));
    }
    public String getShortInfoBuying(){
        return String.format(String.format(Util.colCyan("%-40s")+Util.colRed("%d Kč."), title,price));
    }


    public void setBorrowed(Boolean borrowed, ALoan borrowedReference) {
        this.borrowed = borrowed;
        this.borrowedReference = borrowed ? borrowedReference : null;
    }

    @Override
    public Integer getId() {
        return null;
    }

}
