package cz.robodreams.javadeveloper.project.control.server;


import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.Role;
import cz.robodreams.javadeveloper.project.control.common.SocketReadWriter;
import cz.robodreams.javadeveloper.project.control.server.service.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.map.HashedMap;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import static cz.robodreams.javadeveloper.project.control.common.Const.doesThisTaskContain;
import static cz.robodreams.javadeveloper.project.control.common.Const.isNotNull;


public class ServerHandler extends Thread {

    @Getter
    @Setter
    private Role role = Role.NONE;

    private SocketReadWriter communicator;
    private Socket socket;
    private Map<String, ServiceProvider> carusel = new HashedMap<>();
    public static String threadName;

    private MessageTransfer messageTransfer;

    public ServerHandler(Socket socket, Integer id) {

        communicator = new SocketReadWriter(socket);
        this.socket = socket;
        threadName = "Thread " + id + ": ";

        try {
            this.socket.setSoTimeout(200);
        } catch (IOException e) {
            System.out.println(threadName + "Server socket nenastartoval.");
            throw new RuntimeException(e);
        }
        System.out.println(threadName + "Server socket OK.");
    }

    @Override
    public void run() {

        carusel.put(Const.EMPTY, new ServiceProviderEmpty());
        carusel.put(Const.MESSAGES_FIRST_CONNECT, new ServiceProviderRole());

        carusel.put(Const.RETURN_TO_ROOT, new ServiceProviderReturnToRoot());
        carusel.put(Const.MESSAGE_SEND_DLG_LIST, new ServiceProviderListArticle());
        carusel.put(Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE, new ServiceProviderListAccordingGenre());
        carusel.put(Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_SHOW, new ServiceProviderListAccordingGenreShow());

        ServiceProvider serviceProvider;

        while (true) {

            messageTransfer = communicator.receiveStream();
            System.out.println("Test v karuselu: " + messageTransfer.task());

            if (carusel.containsKey(messageTransfer.task())) {
                do {
                    serviceProvider = carusel.get(messageTransfer.task());

                    if (Const.isNotNull.test(serviceProvider)) {

                        System.out.println("Volání : " + serviceProvider.getClass().getSimpleName());
                        messageTransfer = ((ServiceProvider) serviceProvider).run(messageTransfer, this);

                        if (doesThisTaskContain.test(messageTransfer, Const.EXIT)) {
                            break;
                        }
                    }
                } while (isNotNull.test(messageTransfer) && isNotNull.test(messageTransfer.loop()) && messageTransfer.loop());

                communicator.sendStream(messageTransfer);
                if (doesThisTaskContain.test(messageTransfer, Const.EXIT)) {
                    System.out.println("Poslední message " + messageTransfer);
                    break;
                }

            } else {
                throw new RuntimeException("Chybí task v karuselu.");
            }
        }
        communicator.free();
    }

}
