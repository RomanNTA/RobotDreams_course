package cz.robodreams.javadeveloper.project.control.server;


import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.control.common.Const;

import java.io.IOException;
import java.net.ServerSocket;

public final class ServerImpl implements Server {

    public final Service service = Service.getInstance();
    private static ServerImpl INSTANCE = new ServerImpl();


    public static ServerImpl getInstance() {
        return INSTANCE;
    }

    private ServerImpl() {

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
