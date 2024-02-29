package cz.robodreams.javadeveloper.project.event;

import cz.robodreams.javadeveloper.project.books.IBooksShopping;
import cz.robodreams.javadeveloper.project.common.Util;

public class EventBuyBooks extends AEvent {


    private Integer moneyToBuyBooks;
    private IBooksShopping booksShopping;

    public EventBuyBooks() {
        super(IEventType.BUY_BOOKS, 0,0);
    }

    @Override
    public Boolean run() {
        if (booksShopping == null) {
            throw new NullPointerException("Chybí dodavatel kníh.");
        }

        if (moneyToBuyBooks <= 0) {
            throw new IllegalArgumentException("Chybí peníze na nákup kníh.");
        }

        print.add(String.format("Knihovna investuje do nákupu knih " + Util.colRed("%d")  +" Kč",moneyToBuyBooks));
        printEvent();

        Integer i = booksShopping.buyBooks(moneyToBuyBooks);
        System.out.println("\t... zaplatíme dodavateli za knihy " + i + " Kč.");
        EventAccount.getInstance().setNewCash(-i).setMessage("Úhrada za nové knihy.").run();

        return true;
    }

    @Override
    public Boolean isClose() {
        return true;
    }

    public EventBuyBooks setMoneyToBuyBooks(Integer moneyToBuyBooks) {
        this.moneyToBuyBooks = moneyToBuyBooks;
        return this;
    }

    public EventBuyBooks setSuplier(IBooksShopping supplier) {
        this.booksShopping = supplier;
        return this;
    }



}
