package cz.robodreams.javadeveloper.project.event;

import cz.robodreams.javadeveloper.project.lending.ILending;
import cz.robodreams.javadeveloper.project.lending.ILendingConst;

public class EventBorrowBook extends AEvent {

    private ILending lendingManager;
    private Integer count;

    public EventBorrowBook() {
        super(IEventType.I_WANT_TO_BORROW_A_BOOK, 2, ILendingConst.BORROWED_LEADTIME);
    }

    @Override
    public Boolean run() {

        if (lendingManager == null) {
            System.out.println("Nyní není zájem o vypůjčky knih.");
            return false;
        }

        if (count <= 0) {
            System.out.println("Nyní není zájem o vypůjčky knih.");
            return false;
        }

        lendingManager.generator(count);
        //EventAccount.getInstance().setNewCash(-i).setMessage("Úhrada za nové knihy.").run();

        return true;
    }


    public EventBorrowBook setSuplier(ILending lendingManager) {
        this.lendingManager = lendingManager;
        return this;
    }

    public EventBorrowBook setCount(int count) {
        this.count = count;
        return this;
    }


}
