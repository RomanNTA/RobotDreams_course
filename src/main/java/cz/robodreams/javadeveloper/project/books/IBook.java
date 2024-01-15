package cz.robodreams.javadeveloper.project.books;

public interface IBook {


    void show(Boolean shortLongFormat);


    Boolean getBorrowed();

    void setBorrowed(Boolean borrowed);



    Integer getIdBook();

    String getTitle();

    String getAuthor();

    Integer getNumberOfPages();

    Integer getPrice();

    String getIsbn();

    String getEan();

    String getCustody();

    String getGenre();

    String getPublisher();

    Integer getProfit();


}
