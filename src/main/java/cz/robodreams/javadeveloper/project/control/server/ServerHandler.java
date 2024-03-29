package cz.robodreams.javadeveloper.project.control.server;


import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.Role;
import cz.robodreams.javadeveloper.project.control.common.SocketReadWriter;
import cz.robodreams.javadeveloper.project.control.server.service.*;
import cz.robodreams.javadeveloper.project.users.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.map.HashedMap;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.function.Supplier;

import static cz.robodreams.javadeveloper.project.control.common.Const.doesThisTaskEquals;
import static cz.robodreams.javadeveloper.project.control.common.Const.isNotNull;


public class ServerHandler extends Thread {

    public static String threadName;

    @Getter
    @Setter
    private Role role = Role.NONE;

    private String userName;

    private SocketReadWriter communicator;
    private Socket socket;
    private Map<String, Supplier<ServiceProvider>> providerTask = new HashedMap<>();
    private MessageTransfer messageTransfer;

    @Getter
    @Setter
    private User client;

    public ServerHandler(Socket socket, Integer id) {

        threadName = "Thread " + id + ": ";
        try {
            try {
                this.socket = socket;

                communicator = new SocketReadWriter(socket);
                //this.socket.setKeepAlive(true);
                this.socket.setSoTimeout(200);
                System.out.println(threadName + "Server socket OK.");

            } catch (IOException e) {
                System.out.println(threadName + "Server socket nenastartoval.");
                //throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {

        providerTask.put(Const.EMPTY, ServiceProviderEmpty::new);
        providerTask.put(Const.MESSAGES_FIRST_CONNECT, ServiceProviderRole::new);
        ServiceProvider serviceProvider;

        while (true) {

            messageTransfer = communicator.receiveStream();
            if (providerTask.containsKey(messageTransfer.task())) {
                do {
                    serviceProvider = (providerTask.get(messageTransfer.task()).get());
                    if (Const.isNotNull.test(serviceProvider)) {
                        messageTransfer = ((ServiceProvider) serviceProvider).run(messageTransfer, this);
                        if (doesThisTaskEquals.test(messageTransfer, Const.EXIT)) {
                            break;
                        }
                    }
                } while (isNotNull.test(messageTransfer) && isNotNull.test(messageTransfer.loop()) && messageTransfer.loop());

                communicator.sendStream(messageTransfer);
                if (doesThisTaskEquals.test(messageTransfer, Const.EXIT)) {
                    break;
                }

            } else {
                throw new RuntimeException("Chybí task v karuselu.");
            }
        }
        communicator.free();
    }

    public void setRole(Role role) {

        this.role = role;
        if (role == Role.DRIVER) {
            providerTask.put(Const.DRIVER_RETURN_TO_ROOT, ServiceProviderDriverReturnToRoot::new);
            providerTask.put(Const.DRIVER_SEND_DLG_LIST, ServiceProviderDriverListArticle::new);
            providerTask.put(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE, ServiceProviderDriverListAccordingGenre::new);
            providerTask.put(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW, ServiceProviderDriverListAccordingGenreShow::new);
            providerTask.put(Const.DRIVER_SEND_DLG_LIST_BORROWED, ServiceProviderDriverListArticleBorrowed::new);
            providerTask.put(Const.DRIVER_SEND_DLG_LIST_ALL_USERS, ServiceProviderDriverListAllUsers::new);
            providerTask.put(Const.DRIVER_SEND_DLG_RELEASE_BOOKS, ServiceProviderDriverReleaseBooks::new);
            providerTask.put(Const.DRIVER_SEND_DLG_RELEASE_CLIENT_SELECTION, ServiceProviderDriverReleaseClientSelection::new);
        }
        if (role == Role.CLIENT) {
            providerTask.put(Const.CLIENT_RETURN_TO_ROOT, ServiceProviderClientReturnToRoot::new);
            providerTask.put(Const.CLIENT_SEND_DLG_LOGIN_USER, ServiceProviderClientLoginUser::new);
        }
    }

    public void setClient(User client) {

        this.client = client;
        if (isNotNull.test(client)) {

            userName = String.format(Util.colWhite(" : %s ") + Util.colPurple("%s %s"), client.getGender(), client.getName(), client.getSurname());

            providerTask.put(Const.CLIENT_SEND_DLG_LOAN_LIST, ServiceProviderClientLoanList::new);
            providerTask.put(Const.CLIENT_SEND_DLG_LOAN_BOOKS, ServiceProviderClientLoan::new);
            System.out.println("providerTask SIZE : " + providerTask.size());
        }
    }

    public String getUserName(){
        return isNotNull.test(userName) ? userName : "";
    };
}
