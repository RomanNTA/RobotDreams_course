package cz.robodreams.javadeveloper.project.control.server;


import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.SocketReadWriter;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static cz.robodreams.javadeveloper.project.control.common.Const.doesThisTaskContain;


public class ServerHandler extends Thread {

    private String nameOfRoom;
    private String nameOfPerson;
    private Integer counter;
    private SocketReadWriter communicator;
    private Socket socket;
    private Map<String, List<String>> stack;
    private Predicate<String> isStringFullAndNotNull;
    public static String th;

    public ServerHandler(Socket socket, Map<String, List<String>> stack, Integer id) {

        communicator = new SocketReadWriter(socket);
        this.socket = socket;
        this.stack = stack;
        th = "Thread " + id + ": ";

        try {
            this.socket.setSoTimeout(200);
        } catch (IOException e) {
            System.out.println(th + "Server socket nenastartoval.");
            throw new RuntimeException(e);
        }
        System.out.println(th + "Server socket OK.");
        counter = 0;
    }


    public MessageTransfer getMessage() {

//        String inputString;
//        MessageTransfer m;
//        while (true) {
//
//            inputString = null;
////            inputString = communicator.receiveLine();
//
//            if (isStringFullAndNotNull.test(inputString)) {
//                m = new MessageTransfer(communicator.parseByPosition(inputString, 0).trim(), communicator.parseByPosition(inputString, 1).trim());
//                break;
//            }
//
//            if (isStringFullAndNotNull.test(this.nameOfRoom)) {
//                if ((new CommunicatorGetCountMessages(this.nameOfRoom).go(stack)) > counter) {
//                    readFromRoom();
//                }
//            }
//        }
//        return m == null ? new MessageTransfer(Const.EXIT, "") : m;
        return null;
    }


    @Override
    public void run() {

        MessageTransfer mt;
        try {
            while (true) {

                mt = communicator.receiveStream();

                if (doesThisTaskContain.test(mt, Const.MESSAGES_FIRST_CONNECT)) {

                    //prepareMtForFirstConnect();

                    System.out.println(th + "MESSAGES_FIRST_CONNECT");

                    List<String> menu = new ArrayList<>();
                    menu.add("Knihovnice");
                    menu.add("Klient knihovny");

                    MessageTransfer mta = new MessageTransfer(Const.MESSAGES_SEND_MENU, "",
                            Const.MESSAGE_DLG2_WHAT_ROLE_label, Const.MESSAGE_DLG2_WHAT_ROLE_text, menu, 0);

                    communicator.sendStream(mta);

                }

                if (doesThisTaskContain.test(mt, Const.MESSAGES_PRINT_TEXT)) {
                    System.out.println(th + "MESSAGES_PRINT_TEXT");

                    List<String> menu = new ArrayList<>();
                    menu.add("Text první řádek");
                    menu.add("Text druhý řádek");

                    MessageTransfer mta = new MessageTransfer(Const.MESSAGES_PRINT_TEXT, "", "", "", menu, 0);
                    communicator.sendStream(mta);

                    MessageTransfer messageTransfer = new MessageTransfer(Const.MESSAGES_SEND_MENU, "", "", "", , null, 0);
                }




////                    PrintOutputImpl ee = new PrintOutputImpl(Const.MESSAGE_DLG2_WHAT_ROLE_label, Const.MESSAGE_DLG2_WHAT_ROLE_label);
////                    ee.add("Knihovnice").add("Klient knihovny");
//                    continue;
//                }

//
//                if (mt.task().contains(Const.MESSAGES_SEND_NAME)) {
//                    nameOfPerson = mt.message();
//                    continue;
//                }
//
//                if (mt.task().contains(Const.MESSAGES_CONNECT_TO_ROOM)) {
//                    this.nameOfRoom = mt.message();
//                    new CommunicatorAddRoom(nameOfRoom).go(stack);
//                    continue;
//                }

                //if (mt.task().contains(Const.MESSAGES_CLIENT_TO_SERVER)) {
                if (doesThisTaskContain.test(mt, Const.MESSAGES_CLIENT_TO_SERVER)) {
                    System.out.println(th + "MESSAGES_CLIENT_TO_SERVER " + mt.message());
                    //writeToRoom(mt.message());
                    continue;
                }

//                if (mt.task().contains(Const.EXIT)) {
//                    break;
//                }

            }
        } finally {
            try {
                communicator.free();
                socket.close();
            } catch (IOException e) {
                System.out.println(th + "SERVER SOCKET: ERROR END");
            }
        }
    }


    public void writeToRoom(String anyMessage) {

//        String message = String.format(Const.STR3_ANY_MESSAGE, nameOfPerson, anyMessage);
//        //new CommunicatorSendMessage(nameOfRoom, message).go(stack);
//        readFromRoom();
    }

    public void readFromRoom() {

//        List<String> outputBuffer = new ArrayList<>();
//        List<String> buff = new CommunicatorGetMessages(nameOfRoom, counter).go(stack);
//        if (buff == null || buff.isEmpty()) {
//            return;
//        }
//        int i = 0;
//        while (i < buff.size()) {
//            outputBuffer.add(String.format(Const.MESSAGE_PATTERN, Const.MESSAGES_SERVER_TO_CLIENT, buff.get(i++)));
//            counter++;
//        }
//
//        final int ee = stack.get(nameOfRoom).size();
//        communicator.sendLines(outputBuffer);
    }

}
