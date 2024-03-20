package cz.robodreams.javadeveloper.project.control.client;


import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.ServiceThread;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

public class CommunicatorGetMenu extends ServiceThread<MessageTransfer> {

    public MessageTransfer go(MessageTransfer messageTransfer, ClientImpl caller) {

        if (!Const.isNotNull.test(messageTransfer)) return null;

        this.task = () -> {

            MessageTransfer result = null;
            try {
//                synchronized (caller) {
//                }
                result = caller.getMenu(messageTransfer);

            } catch (NullPointerException e) {
                System.out.println(ServerHandler.th + "Chyba: CommunicatorSendMessage: " + e.getMessage());
            }
            System.out.println(ServerHandler.th + "CommunicatorGetMenu: " + result.output());

            return result;
        };

        return isAllowedRun ? start() : null;
    }

}


