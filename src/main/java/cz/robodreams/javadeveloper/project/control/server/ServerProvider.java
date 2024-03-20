package cz.robodreams.javadeveloper.project.control.server;

public class ServerProvider  {


    public static Server getInstance() {

        return new ServerImpl();
    }

    /**
     *  Zkoušel jsm i statický přístup pro test - neprošel
     */

//    private static Server INSTANCE;
//
//    public static Server getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new ServerImpl();
//        }
//        return INSTANCE;
//    }
//    public static Server getInstance() {
//        return ServerImpl.getInstance();
//    }

    public static void main(String[] args) {

        new ServerImpl().start();
    }


}
