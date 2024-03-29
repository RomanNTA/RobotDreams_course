package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Subjects;
import cz.robodreams.javadeveloper.project.users.User;

import java.util.List;
import java.util.Map;

public interface Lending<ILoan>  extends Subjects<ILoan> {

    List<String> showBorrowedBooks(ShowSubjectItems showItems);

    List<String> showBorrowedBooksFromUser(User user);

    Boolean newLoan(User user, Book book);

    Boolean removeLoan(ILoan loan);

    Map<User, Long> driverReleaseClientSelection();

    Map<Integer, ILoan> driverReleaseBooks(Integer userId);

}
