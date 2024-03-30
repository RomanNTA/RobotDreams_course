package cz.robodreams.javadeveloper.project.control.server;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Books;
import cz.robodreams.javadeveloper.project.article.interfaces.Article;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.lending.Lending;
import cz.robodreams.javadeveloper.project.users.Users;

public interface Server {

    void start();

    Users getUsers();

    ArticlesRepository getArticle() ;

    Lending getLending();

}
