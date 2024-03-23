package cz.robodreams.javadeveloper.project.control.client;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;

public class CommunicatorPrintMessage implements Runnable {

    private MessageTransfer messageTransfer;

    public CommunicatorPrintMessage(MessageTransfer messageTransfer) {
        this.messageTransfer = messageTransfer;
    }

    @Override
    public void run() {
        if (!Const.isNotNull.test(messageTransfer)) return;
        messageTransfer.menu().forEach(System.out::println);
    }


}


