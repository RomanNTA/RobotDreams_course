package cz.robodreams.javadeveloper.project;

import cz.robodreams.javadeveloper.project.control.server.Server;
import cz.robodreams.javadeveloper.project.control.server.ServerImpl;

public class ServerApp {

    public static void main(String[] args) {

        ServerImpl.getInstance().start();
        System.exit(0);
    }

}
