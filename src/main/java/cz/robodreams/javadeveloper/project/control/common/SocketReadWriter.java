package cz.robodreams.javadeveloper.project.control.common;


import cz.robodreams.javadeveloper.project.control.common.exceptions.MyRuntimeExceptionForSocketComunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;

public class SocketReadWriter {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    Consumer<AutoCloseable> closeResources = (resource) -> {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
            }
        }
    };

    private void thisWait(int waitLength) {
        try {
            synchronized (this) {
                wait(waitLength);
            }
        } catch (InterruptedException e) {
        }
    }


    public SocketReadWriter(Socket socket) {

        this.socket = socket;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void free() {
        closeResources.accept(outputStream);
        closeResources.accept(inputStream);
        closeResources.accept(socket);
    }

    public <T extends MessageTransfer> void sendStream(T transferObject) {

        try {
            if (!socket.isClosed()) {
                outputStream.writeObject(transferObject);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new MyRuntimeExceptionForSocketComunication("sendStream" + e.getMessage());
        }
    }

    public <T extends MessageTransfer> T receiveStream() {

        T byteStream = null;
        try {
            thisWait(10);
            byteStream = (T) inputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            return (T) MessageTransfer.builder().task(Const.EMPTY).build();
        }
        return (T) ((byteStream != null) ? byteStream : MessageTransfer.builder().task(Const.EMPTY).build());
    }


}
