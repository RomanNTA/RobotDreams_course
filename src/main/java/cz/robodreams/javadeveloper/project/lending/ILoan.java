package cz.robodreams.javadeveloper.project.lending;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.users.User;

import java.time.LocalDateTime;
import java.util.List;


public interface ILoan {

    void show(ShowSubjectItems showItems);
    String getShortInfo();
    List<String> getResultShow(ShowSubjectItems showItems);
    User getUser();
    Book getBook();
    LocalDateTime getSinceWhen();
    void setSinceWhen(LocalDateTime sinceWhen);
    LocalDateTime getUntilWhen();
    void setUntilWhen(LocalDateTime untilWhen);
    StatusBorrow getStatus();
    void setStatus(StatusBorrow status);


}
