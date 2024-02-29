package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.common.ITerminalColorConst;
import cz.robodreams.javadeveloper.project.lending.ALoan;
import cz.robodreams.javadeveloper.project.common.Util;

public class Book implements IBook {

    private Integer idBook;

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


    public Book(Integer idBook, String title, String author, Integer numberOfPages,
                Integer price, String isbn, String ean, String custody, String genre,
                String publisher, Integer profit) {
        this.idBook = idBook;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.price = price;
        this.isbn = isbn;
        this.ean = ean;
        this.custody = custody;
        this.genre = genre;
        this.publisher = publisher;
        this.profit = profit;
    }

    /**
     * @param shortLongFormat ... true = dlouhé
     */
    public void show(Boolean shortLongFormat) {
        String s = "";
        s += String.format("| Název: " + Util.colCyan("%40s"), title);
        s += String.format("  Nakladatelství : " + Util.colCyan("%15s"), publisher);

        if (shortLongFormat) {

            s += String.format("\n| Autor: " + Util.colCyan("%40s"), author);
            s += String.format("  Počet stran : " + Util.colCyan("%18s \r\n"), numberOfPages);

            s += String.format("| Obor:  " + Util.colCyan("%40s"), genre);
            s += String.format("  Vazba: " + Util.colCyan("%25s \r\n"), custody);

            s += shortLongFormat ? String.format("| ISBN: %s. EAN: %s. Poplatek: " + Util.colRed("%d") + " Kč. Cena : " + Util.colRed("%d") + " Kč.",
                    isbn, ean, profit, price) : "";

        }

        if (!borrowed) {
            s += String.format("\r\n| Kniha je nyní : %s.", Util.colGreen("k dispozici"));
        }

        System.out.println(s);

        if (borrowed) {
            borrowedReference.show(true);
        }

    }

    public String getShortInfo(){
        return String.format(String.format(Util.colCyan("%-40s"), title));
    }
    public String getShortInfoBuying(){
        return String.format(String.format(Util.colCyan("%-40s")+Util.colRed("%d Kč."), title,price));
    }



    /**
     * Getter + setter
     */
    public ALoan getBorrowedReference() {
        return borrowedReference;
    }

    public Boolean getBorrowed() {
        return borrowed;
    }


    public void setBorrowed(Boolean borrowed, ALoan borrowedReference) {
        this.borrowed = borrowed;
        this.borrowedReference = borrowed ? borrowedReference : null;
    }

    /**
     * Jen gettery
     */
    public Integer getIdBook() {
        return idBook;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public Integer getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEan() {
        return ean;
    }

    public String getCustody() {
        return custody;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getProfit() {
        return profit;
    }

}
