package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.List;

public class ServiceProviderDriverListAllUsers extends ServiceProviderImpl implements ServiceProvider {

    public ServiceProviderDriverListAllUsers() {
        myId = Const.DRIVER_SEND_DLG_LIST_ALL_USERS;
    }


    @Override
    public MessageTransfer sendOffer() {

        System.out.println(ServerHandler.threadName + myId);

        List<String> menu = users.getUsersDriver(ShowSubjectItems.LONG_FORMAT);
        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .replyTask(Const.DRIVER_RETURN_TO_ROOT)
                .menu(menu)
                .loop(false)
                .build();

    }
//
//    @Override
//    public MessageTransfer processAnswer() {
//
//    }
//
//    @Override
//    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
//
//
//        System.out.println(ServerHandler.threadName + "MESSAGE_SEND_DLG_LIST_ALL_USERS - vstup");
//
//        if (Const.doesThisTaskEquals.test(messageTransfer, myId) &&
//                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {
//
//            System.out.println(ServerHandler.threadName + "MESSAGE_SEND_DLG_LIST_ALL_USERS");
//
//            List<String> menu = users.getUsersDriver(ShowSubjectItems.LONG_FORMAT);
//
//            return MessageTransfer.builder()
//                    .task(Const.MESSAGES_PRINT_TEXT)
//                    .replyTask(Const.DRIVER_RETURN_TO_ROOT)
//                    .menu(menu)
//                    .loop(false)
//                    .build();
//
//        } else {
//            return MessageTransfer.builder().task(Const.DRIVER_RETURN_TO_ROOT).loop(true).build();
//        }
//        //return MessageTransfer.builder().task(Const.EMPTY).build();
//    }

}
