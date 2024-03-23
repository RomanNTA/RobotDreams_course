package cz.robodreams.javadeveloper.project.control.client;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.SocketReadWriter;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import static cz.robodreams.javadeveloper.project.control.common.Const.doesThisTaskContain;
import static cz.robodreams.javadeveloper.project.control.common.Const.isNotNull;

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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.socket.setSoTimeout(20);
            ClientImpl.isRunningHandler.set(true);

        } catch (SocketException e) {
            throw new RuntimeException("Chyba: clientSocket.");
        }
        console = new Scanner(System.in, "UTF-8");

    }


    public MessageTransfer getMessage() {

//        String inputString;
//        PrintOutputImpl newDialog;
//        MessageTransfer messageTransfer;
//        while (true) {
//            messageTransfer = ((MessageTransfer) <MessageTransfer>communicator.receiveStream();
//        }
//        return messageTransfer != null ? messageTransfer : new MessageTransfer(Const.CLIENT_ERROR, "public MessageTransfer getMessage");

//            if (!(sendMessageBuffer.isEmpty())) {
//                String s;
//                synchronized (sendMessageBuffer) {
//                    s = sendMessageBuffer.poll();
//                }
//                communicator.sendLine(s);
//            }


//            inputString = communicator.receiveLine();


//            if (newDialog != null)
//                newDialog.show();

//            if (inputString != null && !inputString.isBlank()) {
//                messageTransfer = new MessageTransfer(communicator.parseByPosition(inputString, 0).trim(), communicator.parseByPosition(inputString, 1).trim());
//                break;
//            }

        return null;
    }

    @Override
    public void run() {

        try {

            MessageTransfer mt;
            communicator = new SocketReadWriter(socket);

            while (true) {
                mt = null;

                if (!ClientImpl.isRunningHandler.get()) {
                    break;
                }

                if (!sendMessageBuffer.isEmpty()) {
                    synchronized (sendMessageBuffer) {
                        mt = sendMessageBuffer.poll();
                    }
                    System.out.println("-> " + mt.task() + " - " + mt.replyTask());
                    communicator.sendStream(mt);
                }

                mt = communicator.receiveStream();

                if (doesThisTaskContain.test(mt, Const.EMPTY)) {
                    continue;
                }

//                mt = new MessageTransfer(Const.MESSAGES_FIRST_CONNECT,"První pokus");
//                communicator.sendStream(mt);

                if (doesThisTaskContain.test(mt, Const.MESSAGES_FIRST_CONNECT)) {

                    synchronized (messageBuffer) {
                        messageBuffer.add(mt);
                    }
                    //System.out.println(" zapsane " + mt.replyTask());
                    continue;
                }

                if (isNotNull.test(mt)) {
                    System.out.println(mt.task() + " - " + mt.replyTask());
                }

//                if (mt.task().contains(Const.MESSAGES_CLIENT_TO_SERVER) || mt.task().contains(Const.MESSAGES_SERVER_TO_CLIENT)) {
//
//                    synchronized (messageBuffer) {
//                        messageBuffer.add(mt);
//                    }
//                    System.out.println(mt.replyTask());
//                    continue;
//                }

                if (doesThisTaskContain.test(mt, Const.MESSAGES_SEND_MENU)) {
                    //MessageTransfer messageTransfer = new <MessageTransfer>CommunicatorGetMenu().go(mt, console);
                    sendMessageBuffer.add(new <MessageTransfer>CommunicatorGetMenu().go(mt, console));
                    continue;
                }

                if (doesThisTaskContain.test(mt, Const.MESSAGES_PRINT_TEXT)) {
                    // System.out.println("MESSAGES_PRINT_TEXT: " + mt.menu().size());
                    new CommunicatorPrintMessage(mt).run();
                    sendMessageBuffer.add(MessageTransfer.builder().task(mt.replyTask()).build());

                    continue;
                }

                if (mt.task().contains(Const.EXIT) || mt.task().contains(Const.CLIENT_ERROR)) {
                    break;
                }

            }


//        } catch (
//                RuntimeException e) {
//            System.out.println("Chyba 2: " + e.getMessage());


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
