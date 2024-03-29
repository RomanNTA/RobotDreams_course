package cz.robodreams.javadeveloper.project.control.client;


import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerImpl;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class ClientImpl implements Client {

    private List<MessageTransfer> messageBuffer = new ArrayList<>();
    private Queue<MessageTransfer> sendMessageBuffer = new LinkedList<>();
    public static AtomicBoolean isRunningHandler = new AtomicBoolean(false);


    public static ClientImpl getInstance() {
        return new ClientImpl();
    }

    private ClientImpl() {

        isRunningHandler.set(false);

        try {
            new ClientHandler(sendMessageBuffer, messageBuffer, this).start();
        } catch (Exception e) {
            System.out.println("Chyba 1 : " + e.getMessage());
        }

        while (isRunningHandler.equals(false)) {
            try {
                synchronized (this) {
                    wait(5);
                }
            } catch (InterruptedException e) {
            }
        }

        System.out.println("Start klienta.");
        connect();
    }

    private void connect() {
        sendMessageBuffer.add(MessageTransfer.builder().task(Const.MESSAGES_FIRST_CONNECT).build());
    }

}
