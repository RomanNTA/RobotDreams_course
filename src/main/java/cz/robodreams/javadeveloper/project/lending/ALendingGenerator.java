package cz.robodreams.javadeveloper.project.lending;


import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;
import cz.robodreams.javadeveloper.project.control.common.Const;

import java.time.LocalDateTime;

public class ALendingGenerator {

    public ALendingGenerator(SubjectAdd<ILoan> destination, int count) {

        for (int i = 0; i < count; i++) {
            destination.add(get());
        }
    }

    public ILoan get() {

        ILoan loan = ALoan.builder()
                .user(Service.getInstance().getUser().getRandomSubject())
                .book(Service.getInstance().getArticle().<Book>getRandomSubject(Lock.LOCK, ArticleType.BOOKS))
                .sinceWhen(LocalDateTime.now())
                .untilWhen(LocalDateTime.now().plusDays(Const.BORROWED_LEADTIME))
                .status(StatusBorrow.BOOK_BORROWED).build();

        loan.setStatus(StatusBorrow.BOOK_BORROWED);
        return loan;
    }

}
