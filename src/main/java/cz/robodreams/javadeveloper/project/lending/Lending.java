package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.common.Subjects;

public interface Lending<ILoan>  extends Subjects<ILoan> {

    void showBorrowedBooks();
    void showUsersBorrowedBooks();


}
