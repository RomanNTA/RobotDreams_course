package cz.robodreams.javadeveloper.project.control.client;



import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;

import java.util.List;

public interface Client {

    //void setRole(String name);

    //void sendMessage(String replyTask);

    List<MessageTransfer> getAllRoomMessage();

    void killConnect();

}
