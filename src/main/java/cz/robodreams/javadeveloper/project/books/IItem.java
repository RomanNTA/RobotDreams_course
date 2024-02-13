package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.lending.ALoan;

public interface IItem {


    String getShortInfo();

    void show(Boolean shortLongFormat);


    Boolean getBorrowed();

    void setBorrowed(Boolean borrowed, ALoan borrowedReference);

    ALoan getBorrowedReference();





}
