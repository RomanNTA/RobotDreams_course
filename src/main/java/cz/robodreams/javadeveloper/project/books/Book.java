package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.util.TerminalColorConst;

public class Book extends TerminalColorConst implements IBook {

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
        if (shortLongFormat) {
        }
        s += String.format("| Název: " + colCyan("%40s"), title);
        s += String.format("  Nakladatelství : " + colCyan("%15s \r\n"), publisher);

        s += String.format("| Autor: " + colCyan("%40s"), author);
        s += String.format("  Počet stran : " + colCyan("%18s \r\n"), numberOfPages);

        s += String.format("| Obor:  " + colCyan("%40s"), genre);
        s += String.format("  Vazba: " + colCyan("%25s \r\n"), custody);

        s += shortLongFormat ? String.format("| ISBN: %s. EAN: %s. Poplatek: " + colRed("%d") + " Kč. Cena : " + colRed("%d") + " Kč.\r\n",
                isbn, ean, profit, price) : "";

        s += shortLongFormat ? String.format("| Kniha je nyní : %s.", (borrowed ? colRed("zapůjčená") : colGreen("k dispozici"))) : "";

        System.out.println(s);
    }



    /**
     * Getter + setter
     */

    public Boolean getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
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
