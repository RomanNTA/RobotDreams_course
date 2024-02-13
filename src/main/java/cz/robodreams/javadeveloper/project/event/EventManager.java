package cz.robodreams.javadeveloper.project.event;

import cz.robodreams.javadeveloper.project.books.BooksShopping;
import cz.robodreams.javadeveloper.project.books.IArticle;
import cz.robodreams.javadeveloper.project.books.IItem;
import cz.robodreams.javadeveloper.project.common.ASubject;
import cz.robodreams.javadeveloper.project.common.UsefulProc;
import cz.robodreams.javadeveloper.project.lending.ILending;
import cz.robodreams.javadeveloper.project.lending.ILoan;
import cz.robodreams.javadeveloper.project.users.IUser;
import cz.robodreams.javadeveloper.project.users.IUsers;
import cz.robodreams.javadeveloper.project.users.UsersNewMembers;

public class EventManager extends ASubject<IEvent> {

    private IArticle<IItem> article;
    private IUsers users;
    private ILending lending;

    //  private EventAccount account;

    public EventManager(IArticle<IItem> article, IUsers<IUser> users, ILending<ILoan> lending) {
        this.article = article;
        this.users = users;
        this.lending = lending;
//        this.account = EventAccount.getInstance();
//        this.account IEventAdd

    }

    public void add(IEvent value) {
        super.add(repository.size(), value);
        value.run();
    }

    @Override
    public void generator(int count) {

        int i = EventAccount.getInstance().getCash();
        i = random((int) (i * 0.2), (int) (i * 0.8));
        add(new EventDonate(random(3, 8) * 1000));

        i = (int) (EventAccount.getInstance().getCash() * 0.8);



        // chyba dědění
        add(new EventBuyBooks().setSuplier( new BooksShopping()).setMoneyToBuyBooks(i));




        i = (int) (EventAccount.getInstance().getCash() * 0.8);
        add(new EventBuyBooks().setSuplier(new BooksShopping()).setMoneyToBuyBooks(i));

        i = random(10);
        add(new EventNewUsers().setPeople(new UsersNewMembers()).setCountOfNewPerson(i));

        i = random(3);
        add(new EventBorrowBook().setSuplier(lending).setCount(i));

    }


    @Override
    public void show(int id, Boolean shortLongFormat) {
        return;
    }


    private Integer random(int max) {
        return UsefulProc.getRandomId(0, max);
    }

    private Integer random(int min, int max) {
        return UsefulProc.getRandomId(min, max);
    }







}
