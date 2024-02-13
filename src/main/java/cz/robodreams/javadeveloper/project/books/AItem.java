package cz.robodreams.javadeveloper.project.books;

import cz.robodreams.javadeveloper.project.common.TerminalColorConst;
import cz.robodreams.javadeveloper.project.lending.ALoan;

public class AItem extends TerminalColorConst implements IItem{


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



}
