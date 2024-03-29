package cz.robodreams.javadeveloper.project.control.server;


import cz.robodreams.javadeveloper.project.article.ArticlesRepositoryImpl;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.control.common.Const;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Zkoušel jsem vytvřit z toho vlákno - test se nezdařil
 */
//public final class ServerImpl extends Thread implements Server {
public final class ServerImpl implements Server {

    public static void main(String[] args) {
        new ServerImpl().start();
        System.exit(0);
    }

    public final Service service = Service.getInstance();

    public ServerImpl() {

        /**
         * Načtení knih z databaze a odemknutí části.
         */
        //service.getArticle().loadBooks(100);
        service.getArticle().loadArticle();


        /**
         * Vygenerování 10 uživatelů knihovny
         */
        service.getUser().generator(10);

        /**
         * Vygenerování 5 zapůjčení knih
         */
        service.getLending().generator(5);

        service.getArticle().unlockAnyBooks(5);

    }

    @Override
    public void start() {
        int i = 0;
        try (ServerSocket serverSocket = new ServerSocket(Const.SOCKET_PORT)) {
            while (true) {
                new ServerHandler(serverSocket.accept(), i++).start();
            }
        } catch (IOException e) {
            System.out.println("Server (" + (i - 1) + ") není možné spustit.");
        }
    }

}
