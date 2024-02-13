package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.common.ISubject;

public interface ILending<ILoan>  extends ISubject<ILoan> {

    void showBorrowedBooks();
    void showUsersBorrowedBooks();


}
