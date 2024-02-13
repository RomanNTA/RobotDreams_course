package cz.robodreams.javadeveloper.project.event;

import cz.robodreams.javadeveloper.project.lending.ILendingConst;

import java.util.List;

public class EventBorrowBook extends  AEvent{


    public EventBorrowBook() {
        super(IEventType.I_WANT_TO_BORROW_A_BOOK, 2, ILendingConst.BORROWED_LEADTIME);
    }

    @Override
    public Boolean run() {
        return super.run();
    }

    @Override
    public void printEvent() {

    }
}
