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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cz.robodreams.javadeveloper.project.control.common.Const.doesThisTaskContain;
import static cz.robodreams.javadeveloper.project.control.common.Const.isNotNull;


public class ServerHandler extends Thread {

    @Getter
    @Setter
    private Role role = Role.NONE;

    //private String nameOfPerson;

    private Integer counter;
    private SocketReadWriter communicator;
    private Socket socket;
    private Map<String, ServiceProvider> carusel = new HashedMap<>();
    //private Predicate<String> isStringFullAndNotNull;
    public static String threadName;

    private MessageTransfer messageTransfer;


    //public ServerHandler(Socket socket, Map<String, List<String>> stack, Integer id) {
    public ServerHandler(Socket socket, Integer id) {

        communicator = new SocketReadWriter(socket);
        this.socket = socket;
        //this.stack = stack;
        threadName = "Thread " + id + ": ";

        try {
            this.socket.setSoTimeout(200);
        } catch (IOException e) {
            System.out.println(threadName + "Server socket nenastartoval.");
            throw new RuntimeException(e);
        }
        System.out.println(threadName + "Server socket OK.");
        counter = 0;
    }


//    public MessageTransfer getMessage() {
//
////        String inputString;
////        MessageTransfer m;
////        while (true) {
////
////            inputString = null;
//////            inputString = communicator.receiveLine();
////
////            if (isStringFullAndNotNull.test(inputString)) {
////                m = new MessageTransfer(communicator.parseByPosition(inputString, 0).trim(), communicator.parseByPosition(inputString, 1).trim());
////                break;
////            }
////
////            if (isStringFullAndNotNull.test(this.nameOfRoom)) {
////                if ((new CommunicatorGetCountMessages(this.nameOfRoom).go(stack)) > counter) {
////                    readFromRoom();
////                }
////            }
////        }
////        return m == null ? new MessageTransfer(Const.EXIT, "") : m;
//        return null;
//    }

    private Boolean selfFilter(String str) {

        if (str.equals(Const.EMPTY)) {
            System.out.println(".");
        } else {
            System.out.println("*");
        }
        return Const.doesThisTaskContain.test(messageTransfer, str);
    }


    @Override
    public void run() {

        try {

            carusel.put(Const.EMPTY, new ServiceProviderEmpty());
            carusel.put(Const.MESSAGES_FIRST_CONNECT, new ServiceProviderRole());

            carusel.put(Const.RETURN_TO_ROOT, new ServiceProviderReturnToRoot());
            carusel.put(Const.MESSAGE_SEND_DLG_LIST, new ServiceProviderListArticle());

            ServiceProvider serviceProvider;

            while (true) {

                messageTransfer = communicator.receiveStream();
                System.out.println("Test v karuselu: " + messageTransfer.task());

                if (carusel.containsKey(messageTransfer.task())) {

                    int i =0;
                    do {
                        System.out.println("Před karuselem: (" + i + ")");
                        serviceProvider = carusel
                                .entrySet()
                                .stream()
                                .filter(x -> selfFilter(x.getKey()))
                                .map(x -> x.getValue())
                                .findFirst()
                                .get();
                        //} while (messageTransfer.task().contains("RETURN"));

//                    (messageTransfer.task().contains("RETURN"));

                        if (Const.isNotNull.test(serviceProvider)) {
                            System.out.println("Volání : " + serviceProvider.getClass().getSimpleName());
                            messageTransfer = ((ServiceProvider) serviceProvider).run(messageTransfer, this);
                        } else continue;

//                        System.out.println("Za karuselem: (" + i++ + ") Role: " + role.toString() + " LOOP: " + messageTransfer.loop());
//                        System.out.println(" LOOP: " + messageTransfer);
//
//                        System.out.println(" LOOP: " + messageTransfer.loop());
//                        System.out.println(" LOOP: " + isNotNull.test(messageTransfer));
//                        System.out.println(" LOOP: " + isNotNull.test(messageTransfer.loop()));

                    } while (isNotNull.test(messageTransfer) && isNotNull.test(messageTransfer.loop()) && messageTransfer.loop());

                    communicator.sendStream(messageTransfer);

                } else {
                    throw new RuntimeException("Chybí task v karuselu.");
                }

//                if (doesThisTaskContain.test(messageTransfer, Const.MESSAGES_FIRST_CONNECT)) {
//                    communicator.sendStream(messageTransfer);
//                    //communicator.sendStream(prepareDlgChoiceRole());
//                }
//
//                if (doesThisTaskContain.test(messageTransfer, Const.MESSAGES_SEND_MENU)) {
//                    communicator.sendStream(messageTransfer);
//                }
//
//                if (doesThisTaskContain.test(messageTransfer, Const.MESSAGES_PRINT_TEXT)) {
//                    communicator.sendStream(messageTransfer);
//                }




//                if (doesThisTaskContain.test(messageTransfer, Const.MESSAGES_PRINT_TEXT)) {
//                    System.out.println(threadName + "MESSAGES_PRINT_TEXT");
//
//                    List<String> menu = new ArrayList<>();
//                    menu.add("Text první řádek");
//                    menu.add("Text druhý řádek");
//
//                    MessageTransfer mta = new MessageTransfer(Const.MESSAGES_PRINT_TEXT, "", "", "", menu, 0);
//                    communicator.sendStream(mta);
//
//                    MessageTransfer messageTransfer = new MessageTransfer(Const.MESSAGES_SEND_MENU, "", "", "", null, 0);
//                }


////                    PrintOutputImpl ee = new PrintOutputImpl(Const.MESSAGE_DLG2_WHAT_ROLE_label, Const.MESSAGE_DLG2_WHAT_ROLE_label);
////                    ee.add("Knihovnice").add("Klient knihovny");
//                    continue;
//                }

//
//                if (mt.task().contains(Const.MESSAGES_SEND_NAME)) {
//                    nameOfPerson = mt.replyTask();
//                    continue;
//                }
//
//                if (mt.task().contains(Const.MESSAGES_CONNECT_TO_ROOM)) {
//                    this.nameOfRoom = mt.replyTask();
//                    new CommunicatorAddRoom(nameOfRoom).go(stack);
//                    continue;
//                }

//                //if (mt.task().contains(Const.MESSAGES_CLIENT_TO_SERVER)) {
//                if (doesThisTaskContain.test(messageTransfer, Const.MESSAGES_CLIENT_TO_SERVER)) {
//                    System.out.println(threadName + "MESSAGES_CLIENT_TO_SERVER " + messageTransfer.replyTask());
//                    //writeToRoom(mt.replyTask());
//                    continue;
//                }

//                if (mt.task().contains(Const.EXIT)) {
//                    break;
//                }

            }
        } finally {
            try {
                communicator.free();
                socket.close();
            } catch (IOException e) {
                System.out.println(threadName + "SERVER SOCKET: ERROR END");
            }
        }
    }


    public void writeToRoom(String anyMessage) {

//        String replyTask = String.format(Const.STR3_ANY_MESSAGE, nameOfPerson, anyMessage);
//        //new CommunicatorSendMessage(nameOfRoom, replyTask).go(stack);
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


//    public MessageTransfer prepareDlgChoiceRole() {
//        System.out.println(threadName + "MESSAGES_FIRST_CONNECT");
//
//        List<String> menu = new ArrayList<>();
//        menu.add("Knihovnice");
//        menu.add("Klient knihovny");
//
//        return MessageTransfer.builder()
//                .task(Const.MESSAGES_SEND_MENU)
//                .replyTask(Const.MESSAGES_SEND_ROLE)
//                .label(Const.MESSAGE_DLG2_WHAT_ROLE_label)
//                .remark(Const.MESSAGE_DLG2_WHAT_ROLE_text)
//                .menu(menu)
//                .output(0)
//                .loop(false)
//                .build();
//    }


}
