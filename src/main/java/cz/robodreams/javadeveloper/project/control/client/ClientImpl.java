package cz.robodreams.javadeveloper.project.control.client;


import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class ClientImpl implements Client {

    private String role;
    private List<MessageTransfer> messageBuffer = new ArrayList<>();
    private Queue<MessageTransfer> sendMessageBuffer = new LinkedList();
    public static AtomicBoolean isRunningHandler = new AtomicBoolean(false);
    private Scanner console;

    public static void main(String[] args) {
        ClientImpl client = new ClientImpl();
        client.keyboardContrloler();
        client.free();
    }

    public ClientImpl() {
        isRunningHandler.set(false);
        try {
            new ClientHandler(sendMessageBuffer, messageBuffer, this).start();
        } catch (Exception e) {
            System.out.println("Chyba 1 : " + e.getMessage());
        }

        // Až nastartuje handler, můžeš pokračovat
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

        //keyboardContrloler();
    }


    private void keyboardContrloler() {

//        int i = 0;
//        while (!isRunningHandler.get()) {
//            try {
//                synchronized (this) {
//                    wait(500L);
//                }
//            } catch (InterruptedException e) {
//            }
//            i++;
//            if (i == 10) return;
//        }
//        connect();

//        this.console = new Scanner(System.in);

//        setName(getUserInput("Výběr role ",true));
//        connectToRoom(getUserInput("Do kterého pokoje chceš vstoupit ?",true));

//        try {
//            String userInput;
//            while (true) {
//                if (isRunningHandler.equals(false)) {
//                    console.close();
//                    System.out.println("Ukončení koncole.");
//                    return;
//                }
//                userInput = getUserInput(role + " napiš zprávu: ", false);
//
//                if (userInput.equalsIgnoreCase(ControlUtil.EXIT)) {
//                    sendMessageBuffer.add(new MessageTransfer(ControlUtil.EXIT_ROOM, "","","",null,0));
//                    //communicator.sendLine(String.format(Util.MESSAGE_PATTERN,Util.EXIT_ROOM, ""));
//                    //break;
//                } else {
//                    sendMessage(userInput);
//                }
//
//            }
//        } finally {
//            console.close();
//        }
    }

    public void connect() {
        sendMessageBuffer.add(new MessageTransfer(Const.MESSAGES_FIRST_CONNECT, "", "", "", null, 0,false));
    }

    public void printMessage(MessageTransfer mt) {
//    public MessageTransfer printMessage(MessageTransfer mt) {
        Util.line();
        System.out.println(String.format("| %s ", Util.colRed(mt.label())));
        System.out.println(String.format("| %s ", Util.colWhite(mt.remark())));
        mt.menu().forEach(x -> System.out.println(String.format("| %s", x)));
        //return new MessageTransfer(mt.task(), Const.MESSAGES_CLIENT_TO_SERVER, "", "", null, 0);
    }


    public MessageTransfer getMenu(MessageTransfer mt) {

//        try {
//            synchronized (this) {
//                wait(1000L);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        AtomicInteger position = new AtomicInteger(0);
//        Util.line();
//
//        System.out.println(String.format("| %s ", Util.colRed(mt.label())));
//        System.out.println(String.format("| %s ", Util.colWhite(mt.remark())));
//        mt.menu().forEach(x -> System.out.println(String.format("|" + Util.colRed("%3d") + " : %s", position.getAndIncrement(), x)));
//        System.out.println(String.format("| %s ", Util.colWhite("Zadej číslo: ")));
//
//        int result = 0;
//        String stringLine = "";
//
//        console = new Scanner(System.in, "UTF-8");
//        synchronized (console){
//
//            try {
//                //while (!console.hasNextLine()){};
//                stringLine = console.nextLine();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            } finally {
//                console.close();
//            }
//        }
//
//        try {
//            result = Integer.valueOf(stringLine);
//        } catch (NumberFormatException e) {
//            System.out.println("Chybný převod STR to INT.");
//        }
//
//        System.out.println("přijato " + result);
//
//        return MessageTransfer.builder().
//                task(mt.replyTask()).
//                replyTask("reply").
//                output(result).
//                build();
    return null;

    }


//        @Override public void setRole (String role){
//            this.role = role;
//            sendMessageBuffer.add(new MessageTransfer(Const.MESSAGES_SET_CLIENT_ROLE, role, "", "", null, 0));
//        }

//        @SneakyThrows @Override public void sendMessage (String replyTask){
//            sendMessageBuffer.add(new MessageTransfer(Const.MESSAGES_CLIENT_TO_SERVER, replyTask, "", "", null, 0));
//        }

    @Override
    public List<MessageTransfer> getAllRoomMessage() {

        while (!sendMessageBuffer.isEmpty()) {
            try {
                synchronized (this) {
                    wait(5);
                }
            } catch (InterruptedException e) {
            }
        }
        return messageBuffer;
    }

    @SneakyThrows
    @Override
    public void killConnect() {
        sendMessageBuffer.add(MessageTransfer.builder().task(Const.EXIT).build());
        isRunningHandler.set(false);
    }


//    private String getUserInput(String label, boolean isLabel) {
//        if (isLabel) {
//            System.out.println(label);
//        }
//        return console.nextLine();
//    }


    public void free() {
//        console.close();
    }

}
