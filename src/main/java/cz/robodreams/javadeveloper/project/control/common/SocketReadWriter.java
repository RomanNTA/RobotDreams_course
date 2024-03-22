package cz.robodreams.javadeveloper.project.control.common;


import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.function.Consumer;

public class SocketReadWriter {

    private Socket socket;
    private BufferedReader inputString;
    private PrintWriter outputString;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private ByteArrayOutputStream baos;


    Consumer<AutoCloseable> closeResources = (resource) -> {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
            }
        }
    };

    public SocketReadWriter(Socket socket) {

        this.socket = socket;
        try {
//            inputString = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            outputString = new PrintWriter(socket.getOutputStream(), true);
//            outputStream = new ObjectOutputStream(socket.getOutputStream());
//            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {

        }
    }

    public void free() {

        closeResources.accept(outputString);
        closeResources.accept(inputString);
        closeResources.accept(outputStream);
        closeResources.accept(inputStream);
    }


    public void sendLine(String string) {

        try {
            outputString.println(string);
        } catch (Exception e) {
            System.out.println(ServerHandler.threadName + "Chyba: sendLine = " + string);
        }
    }

    public String receiveLine() {

        try {
            return inputString.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public void sendLines(List<String> outputBuffer) {

        try {
            outputBuffer.forEach(outputString::println);
        } catch (Exception e) {
            System.out.println(ServerHandler.threadName + "Chyba: sendLines = " + outputBuffer);
        }
    }

    public String parseByPosition(String input, int position) {

        String[] string = input.split(Const.MESSAGE_DELIMITER);
        return string.length > position ? string[position] : "";
    }


    public <T extends MessageTransfer> void sendStream(T transferObject) {

        try {
            outputStream.writeObject(transferObject);
            outputStream.flush();
//            System.out.println("Odeslaný stream ");
        } catch (IOException e) {
            throw new RuntimeException("Server chyba : sendStream " + e.getMessage());
        }
    }

    public <T extends MessageTransfer> T receiveStream() {
        try {
            T byteStream = null;
            try {
                try {
                    synchronized (this) {
                        wait(10);
                    }
                } catch (InterruptedException e) {
                    //
                }

                try {
                    byteStream = (T) inputStream.readObject();
                } catch (SocketTimeoutException e) {
                    //
                }

            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Server chyba : ClassNotFoundException " + e.getMessage());
            }
            return (T) ((byteStream != null) ? byteStream : new MessageTransfer(Const.EMPTY, "", "", "", null, 0, false));

        } catch (IOException e) {
            return (T) new MessageTransfer(Const.EMPTY, "", "", "", null, 0,false);
        }

    }


}
