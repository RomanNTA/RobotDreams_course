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


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println(getResultShow(showItems));
    }

    @Override
    public List<String> getResultShow(ShowSubjectItems showItems) {

        List<String> result = new ArrayList();
        result.add(String.format("| Název: " + Util.colYellow("%43s"), title));
        result.add(String.format("  Nakladatelství : " + Util.colCyan("%19s\n"), publisher));

        if (showItems == ShowSubjectItems.LONG_FORMAT) {
            result.add(String.format("| Vydávaný od roku :  " + Util.colCyan("%5s"), yearOfFoundation));
            result.add(String.format("  Periodika :  " + Util.colCyan("%10s"), getArticleType().toString()));
            result.add(String.format("  Periodicita: " + Util.colCyan("%23s "), periodicity));
        } else if (showItems == ShowSubjectItems.INFO) {
            result.clear();
            result.add(String.format(Util.colCyan("%-40s"), title));
        }

        return result;
    }

    public void setBorrowed(Boolean borrowed, ALoan borrowedReference) {
        this.borrowed = borrowed;
        this.borrowedReference = borrowed ? borrowedReference : null;
    }

}
