package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.users.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ALoan implements ILoan, ILendingConst {

    @Getter
    private User user;
    @Getter
    private Book book;


    @Setter
    private LocalDateTime sinceWhen;
    @Setter
    private LocalDateTime untilWhen;
    @Setter
    private STATUS_BORROW status;


    public ALoan(User user, Book book,
                 LocalDateTime sinceWhen, LocalDateTime untilWhen,
                 STATUS_BORROW status) {
        this.user = user;
        this.book = book;
        this.sinceWhen = sinceWhen;
        this.untilWhen = untilWhen;
        this.status = status;

        if (status == STATUS_BORROW.BOOK_BORROWED) {
            book.setBorrowed(true);
            book.setBorrowedReference(this);
        }

    }

    /**
     * @param shortLongFormat ... true = dlouhé
     */
    public void show(ShowSubjectItems showItems) {
        String s = "";

        //if (shortLongFormat) {        }

        s += String.format("| %s od : %s", Util.colRed("Zapůjčena"), Util.colGreen(sinceWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))));
        s += String.format(" do : " + Util.colGreen("%s "), untilWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
        s += String.format(" %s " + Util.colPurple("%s %s"), user.getGender(), user.getName(), user.getSurname());
        System.out.println(s);
    }


    public String getShortInfo() {

        String s = "";
        s += String.format(" od : " + Util.colGreen("%s"), sinceWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
        s += String.format(" do : " + Util.colGreen("%s"), untilWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
        return s;
    }

    public LocalDateTime getSinceWhen() {
        return sinceWhen;
    }


    public LocalDateTime getUntilWhen() {
        return untilWhen;
    }


    public STATUS_BORROW getStatus() {
        return status;
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
