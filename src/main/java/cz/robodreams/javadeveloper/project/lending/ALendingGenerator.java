package cz.robodreams.javadeveloper.project.lending;


import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Books;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;
import cz.robodreams.javadeveloper.project.users.Users;


import java.time.LocalDateTime;

public class ALendingGenerator implements ILendingConst {

    //private static Integer counter = 0;

    public ALendingGenerator(SubjectAdd<ILoan> destination, int count, Users users, Books<Book> books) {

        for (int i = 0; i < count; i++) {
            destination.add(/*counter,*/ get(users, books));
            //counter++;
        }
//        for (int i = 0; i < count; i++) {
//            destination.add(counter, get(users, books));
//            counter++;
//        }
    }
    public ILoan get(Users users, Books<Book> books) {

        return new ALoan(
                users.getRandomSubject(),
                books.getRandomSubject(),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(BORROWED_LEADTIME),
                STATUS_BORROW.BOOK_BORROWED);
    }


}
