package cz.robodreams.javadeveloper.project.control.client;

import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;

public class CommunicatorPrintMessage implements Runnable {

    private ClientImpl client;
    private MessageTransfer messageTransfer;


    public CommunicatorPrintMessage(ClientImpl client, MessageTransfer messageTransfer ) {
        this.client = client;
        this.messageTransfer =messageTransfer;
    }

    @Override
    public void run() {
        client.printMessage(messageTransfer);
    }


}


