package cz.robodreams.javadeveloper.project.article.articlebooks;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Periodic;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.lending.ALoan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.function.BiPredicate;

@Builder
@Getter
@Setter
public class PeriodicImpl implements Periodic {

    private Lock locked;
    private ArticleType articleType;
    private Integer idArticle;
    private String title;
    private Integer price; // cena knihy

    private String publisher;
    private Integer yearOfFoundation;
    private String periodicity;

    private Boolean borrowed;
    private ALoan borrowedReference;



    public void show(ShowSubjectItems showItems) {

        BiPredicate<ShowSubjectItems,ShowSubjectItems> testShowItems =(x , y) -> ( x == y);
        boolean view = testShowItems.test(showItems,ShowSubjectItems.LONG_FORMAT);

        String s = "";

        s += String.format("| Název: " + Util.colYellow("%43s"), title);
        s += String.format("  Nakladatelství : " + Util.colCyan("%19s\n"), publisher);

        if ( view) {
            s += String.format("| Vydávaný od roku :  " + Util.colCyan("%5s"), yearOfFoundation);
            s += String.format("  Periodika :  " + Util.colCyan("%10s"), getArticleType().toString());
            s += String.format("  Periodicita: " + Util.colCyan("%23s "), periodicity);

//            s +=  view ? String.format("| ISBN: %s. EAN: %s. Poplatek: " + Util.colRed("%d") + " Kč. Cena : " + Util.colRed("%d") + " Kč.",
//                    isbn, ean, profit, price) : "";
        }
        System.out.println(s);
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

}
