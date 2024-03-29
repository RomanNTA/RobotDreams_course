package cz.robodreams.javadeveloper.project.control.client;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.SocketReadWriter;
import cz.robodreams.javadeveloper.project.control.common.exceptions.MyRuntimeExceptionSocket;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import static cz.robodreams.javadeveloper.project.control.common.Const.doesThisTaskEquals;

public class ClientHandler extends Thread {

    private SocketReadWriter communicator;
    private Socket socket;
    private Queue<MessageTransfer> sendMessageBuffer;
    private List<MessageTransfer> messageBuffer;
    private Scanner console;
    private ClientImpl caller;


    public ClientHandler(Queue<MessageTransfer> sendMessageBuffer, List<MessageTransfer> messageBuffer, ClientImpl caller) {

        this.sendMessageBuffer = sendMessageBuffer;
        this.messageBuffer = messageBuffer;
        this.caller = caller;

        try {
            try {
                this.socket = new Socket(Const.SOCKET_HOST, Const.SOCKET_PORT);
                //this.socket.setKeepAlive(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.socket.setSoTimeout(20);
            ClientImpl.isRunningHandler.set(true);

        } catch (SocketException e) {
            System.out.println(e.getStackTrace());
            //throw new MyRuntimeExceptionSocket(Const.SOCKET_HOST, Const.SOCKET_PORT,"Chyba: clientSocket.", e.getMessage() );
        }
        console = new Scanner(System.in, "UTF-8");

    }

    @Override
    public void run() {

        try {

            MessageTransfer mt;
            communicator = new SocketReadWriter(socket);

            while (true) {
                if (!ClientImpl.isRunningHandler.get()) {
                    break;
                }

                if (!sendMessageBuffer.isEmpty()) {
                    synchronized (sendMessageBuffer) {
                        mt = sendMessageBuffer.poll();
                    }
                    communicator.sendStream(mt);
                }

                mt = communicator.receiveStream();
                if (doesThisTaskEquals.test(mt, Const.EMPTY)) {
                    continue;
                }

                if (doesThisTaskEquals.test(mt, Const.MESSAGES_FIRST_CONNECT)) {
                    synchronized (messageBuffer) {
                        messageBuffer.add(mt);
                    }
                    continue;
                }

                if (doesThisTaskEquals.test(mt, Const.MESSAGES_SEND_MENU)) {
                    sendMessageBuffer.add(new <MessageTransfer>CommunicatorShowMenuVar1().go(mt, console));
                    continue;
                }

                if (doesThisTaskEquals.test(mt, Const.MESSAGES_SEND_MENU_CHOICE_BOOKS)) {
                    sendMessageBuffer.add(new <MessageTransfer>CommunicatorShowMenuVar2().go(mt, console));
                    continue;
                }

                if (doesThisTaskEquals.test(mt, Const.MESSAGES_PRINT_TEXT)) {
                    new CommunicatorPrintMessage(mt).run();
                    sendMessageBuffer.add(MessageTransfer.builder().task(mt.replyTask()).build());
                    continue;
                }

                if (mt.task().contains(Const.EXIT) || mt.task().contains(Const.CLIENT_ERROR)) {
                    break;
                }
            }

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Chyba 3: " + e.getMessage());
            }
        }

        console.close();
        ClientImpl.isRunningHandler.set(false);
    }
}
