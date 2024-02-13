package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.books.IBook;
import cz.robodreams.javadeveloper.project.users.IUser;

import java.time.LocalDateTime;


public interface ILoan {


    void show(Boolean shortLongFormat);

    IUser getUser();

    IBook getBook();


    LocalDateTime getSinceWhen();

    void setSinceWhen(LocalDateTime sinceWhen);

    LocalDateTime getUntilWhen();

    void setUntilWhen(LocalDateTime untilWhen);


    ILendingConst.STATUS_BORROW getStatus();

    void setStatus(ILendingConst.STATUS_BORROW status);


}
