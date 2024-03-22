package cz.robodreams.javadeveloper.project.control.server;


import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.control.common.Const;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Zkoušel jsem vytvřit z toho vlákno - test se nezdařil
 */
//public final class ServerImpl extends Thread implements Server {
public final class ServerImpl implements Server {

    //public static final Map<String, List<String>> stack = new HashMap<>();


    public static void main(String[] args) {
        new ServerImpl().start();
    }


    /**
     * Zkoušel jsem statiku - test se nezdařil
     */
//    private static Server INSTANCE;
//
//    static {
//        initializeInstance();
//    }
//
//    private ServerImpl() {}
//
//    public static void initializeInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new ServerImpl();
//            INSTANCE.start();
//        }
//    }
//
//    public static Server getInstance() {
//        return INSTANCE;
//    }


    public final Service service = Service.getInstance();

    public ServerImpl() {


        /**
         * Načtení knih z databaze.
         */
        //service.getArticle().loadBooks(100);
        service.getArticle().loadArticle();

        /**
         * Vygenerování 20 uživatelů knihovny
         */
        service.getUser().generator(20);

        /**
         * Vygenerování 20 zapůjčení knih
         */
        service.getLending().generator(20);

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
