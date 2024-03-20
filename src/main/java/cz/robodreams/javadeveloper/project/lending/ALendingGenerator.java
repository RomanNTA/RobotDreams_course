package cz.robodreams.javadeveloper.project.lending;


import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Books;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.common.SubjectAdd;
import cz.robodreams.javadeveloper.project.users.Users;


import java.time.LocalDateTime;

public class ALendingGenerator implements ILendingConst {

    public ALendingGenerator(SubjectAdd<ILoan> destination, int count) {

        for (int i = 0; i < count; i++) {
            destination.add(get());
        }
    }

    public ILoan get() {

        return new ALoan(
                Service.getInstance().getUser().getRandomSubject(),
                Service.getInstance().getArticle().<Book>getRandomSubject(ArticleType.BOOKS),
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(BORROWED_LEADTIME),
                STATUS_BORROW.BOOK_BORROWED);
    }

}
