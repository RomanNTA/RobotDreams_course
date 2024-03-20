package cz.robodreams.javadeveloper.project.article.interfaces;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Subjects;

import java.util.List;

public interface ArticlesRepository {

    void loadArticle();

    Boolean add(Article value);

    Integer size();

    void show(int id, ShowSubjectItems showItems);


//    Article getRandomSubject(ArticleType articleType);

    <T extends Article> T getRandomSubject(ArticleType articleType);

    <T extends Article> List<T> getList(Lock locked, ArticleType article);

}
