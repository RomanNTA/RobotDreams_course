package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.books.IBook;
import cz.robodreams.javadeveloper.project.common.ITerminalColorConst;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.users.IUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ALoan implements ILoan, ILendingConst {

    private IUser user;
    private IBook book;


    private LocalDateTime sinceWhen;
    private LocalDateTime untilWhen;
    private STATUS_BORROW status;


    public ALoan(IUser user, IBook book,
                 LocalDateTime sinceWhen, LocalDateTime untilWhen,
                 STATUS_BORROW status) {
        this.user = user;
        this.book = book;
        this.sinceWhen = sinceWhen;
        this.untilWhen = untilWhen;
        this.status = status;

        if (status == STATUS_BORROW.BOOK_BORROWED) {
            book.setBorrowed(true, this);
        }

    }


    /**
     * @param shortLongFormat ... true = dlouhé
     */
    public void show(Boolean shortLongFormat) {
        String s = "";

        //if (shortLongFormat) {        }

        s += String.format("| %s od : %s", Util.colRed("Zapůjčena"), Util.colGreen(sinceWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))));
        s += String.format(" do : " + Util.colGreen("%s "), untilWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
        s += String.format(" %s " + Util.colPurple("%s %s"), user.getGender(), user.getName(), user.getSurname());
        System.out.println(s);
    }


    public String getShortInfo(){

        String s = "";
        s += String.format(" od : " + Util.colGreen("%s"),  sinceWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
        s += String.format(" do : " + Util.colGreen("%s" ), untilWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
        return s;
    }




    public IUser getUser() {
        return user;
    }

    public IBook getBook() {
        return book;
    }

    public LocalDateTime getSinceWhen() {
        return sinceWhen;
    }

    public void setSinceWhen(LocalDateTime sinceWhen) {
        this.sinceWhen = sinceWhen;
    }


    public LocalDateTime getUntilWhen() {
        return untilWhen;
    }

    public void setUntilWhen(LocalDateTime untilWhen) {
        this.untilWhen = untilWhen;
    }


    public STATUS_BORROW getStatus() {
        return status;
    }

    public void setStatus(STATUS_BORROW status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "ALoan{" +
                "user=" + user.getName() +
                " books=" + book.getTitle() +
                ", sinceWhen=" + sinceWhen +
                ", untilWhen=" + untilWhen +
                ", status=" + status +
                '}';
    }
}
