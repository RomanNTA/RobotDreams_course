package cz.robodreams.javadeveloper.project.lending;


import cz.robodreams.javadeveloper.project.books.IArticle;
import cz.robodreams.javadeveloper.project.books.IItem;

import cz.robodreams.javadeveloper.project.books.IItemBook;
import cz.robodreams.javadeveloper.project.common.ISubjectAdd;
import cz.robodreams.javadeveloper.project.users.IUser;
import cz.robodreams.javadeveloper.project.users.IUsers;

import java.time.LocalDateTime;

public class ALendingGenerator implements ILendingConst {

    private static Integer counter = 0;

    public ALendingGenerator(ISubjectAdd<ILoan> destination, int count, IUsers<IUser> users, IArticle<IItemBook> books) {

        for (int i = 0; i < count; i++) {
            destination.add(counter, get(users, books));
            counter++;
        }
    }
    public ILoan get(IUsers<IUser> users, IArticle<IItemBook> books) {

        return new ALoan(
                users.getRandomSubject(),
                books.getRandomSubject(),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(BORROWED_LEADTIME),
                STATUS_BORROW.BOOK_BORROWED);
    }


}
