package cz.robodreams.javadeveloper.project;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.control.server.Server;
import cz.robodreams.javadeveloper.project.control.server.ServerImpl;
import cz.robodreams.javadeveloper.project.lending.ILoan;
import cz.robodreams.javadeveloper.project.lending.Lending;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    @Test
    void TestBuyingOneBook() {
        Server server = ServerImpl.getInstance();

        ArticlesRepository bookRepository = server.getArticle();
        Integer countOfBooks = bookRepository.getCount(Lock.UNLOCK, ArticleType.BOOKS);

        Book book = bookRepository.<Book>getRandomSubject(Lock.LOCK, ArticleType.BOOKS);
        book.setLocked(Lock.UNLOCK);

        assertThat(bookRepository.getCount(Lock.UNLOCK, ArticleType.BOOKS)).isEqualTo(countOfBooks + 1);
    }

    @Test
    void TestThatLoanBookIsBlocked() {
        Server server = ServerImpl.getInstance();

        ILoan loan = (ILoan) server.getLending().get(0);
        Book book = loan.getBook();

        assertThat(book.getBorrowed()).isEqualTo(Boolean.TRUE);

    }

    @Test
    void TestRemoveLoanWithTestedBookThatIsReleased() {
        Server server = ServerImpl.getInstance();

        Lending<ILoan> lending = (Lending<ILoan>) server.getLending();

        ILoan loan = lending.get(0);
        Integer countOfLoaned = lending.size();

        Book book = loan.getBook();
        assertThat(book.getBorrowed()).isEqualTo(Boolean.TRUE);

        lending.removeLoan(loan);
        assertThat(book.getBorrowed()).isEqualTo(Boolean.FALSE);

        assertThat(lending.size()).isEqualTo(countOfLoaned - 1);

    }
}
