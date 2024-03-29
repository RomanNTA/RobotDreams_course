package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class ALoan implements ILoan, ILendingConst {

    private Integer loanId;
    private User user;
    private Book book;
    private LocalDateTime sinceWhen;
    private LocalDateTime untilWhen;
    private STATUS_BORROW status;

//    public ALoan(User user, Book book, LocalDateTime sinceWhen, LocalDateTime untilWhen, STATUS_BORROW status) {
//
//        this.user = user;
//        this.book = book;
//        this.sinceWhen = sinceWhen;
//        this.untilWhen = untilWhen;
//        this.status = status;
//
//        if (status == STATUS_BORROW.BOOK_BORROWED) {
//            book.setBorrowed(true);
//            book.setBorrowedReference(this);
//            book.setLocked(Lock.UNLOCK);
//        }
//    }

    public void setStatus(STATUS_BORROW status) {

        if (status == STATUS_BORROW.BOOK_BORROWED) {
            book.setBorrowed(true);
            book.setBorrowedReference(this);
            book.setLocked(Lock.UNLOCK);
        }

        if (status == STATUS_BORROW.BOOK_RETURNED) {
            book.setBorrowed(false);
            book.setBorrowedReference(null);
        }
    }



    @Override
    public List<String> getResultShow(ShowSubjectItems showItems) {
        String s = "";
        List<String> result = new ArrayList<>();
        if (showItems == ShowSubjectItems.LONG_FORMAT) {
            s += String.format("| %s od : %s", Util.colRed("Zapůjčena"), Util.colGreen(sinceWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))));
            s += String.format(" do : " + Util.colGreen("%s "), untilWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
            s += String.format(" %s " + Util.colPurple("%s %s"), user.getGender(), user.getName(), user.getSurname());
            result.add(s);
        } else {
            s += String.format(" od : " + Util.colGreen("%s"), sinceWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
            s += String.format(" do : " + Util.colGreen("%s"), untilWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
            result.add(s);
        }
        return result;
    }

    @Override
    public String getShortInfo() {

        return String.format("od : " + Util.colGreen("%s"), sinceWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))) +
                String.format(" do : " + Util.colGreen("%s"), untilWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
    }

    @Override
    public void show(ShowSubjectItems showItems) {
        getResultShow(showItems).forEach(System.out::println);
    }


}
