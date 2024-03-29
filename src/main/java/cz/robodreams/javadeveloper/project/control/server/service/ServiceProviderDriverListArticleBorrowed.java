package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.List;

public class ServiceProviderDriverListArticleBorrowed extends ServiceProviderImpl implements ServiceProvider {


    public ServiceProviderDriverListArticleBorrowed() {
        myId = Const.DRIVER_SEND_DLG_LIST_BORROWED;
    }

    @Override
    public MessageTransfer sendOffer() {
//        System.out.println(ServerHandler.threadName + myId);
//
//        //List<String> menu = lending.showBorrowedBooks(ShowSubjectItems.LONG_FORMAT);
        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .replyTask(Const.DRIVER_RETURN_TO_ROOT)
                .menu(lending.showBorrowedBooks(ShowSubjectItems.LONG_FORMAT))
                .loop(false)
                .build();
    }

//    @Override
//    public MessageTransfer processAnswer() {
//        return MessageTransfer.builder().task(Const.DRIVER_RETURN_TO_ROOT).loop(true).build();
//    }

//    @Override
//    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
//
//        myId = Const.DRIVER_SEND_DLG_LIST_BORROWED;
//        System.out.println(ServerHandler.threadName + "MESSAGE_SEND_DLG_LIST_BORROWED - vstup");
//
//        if (Const.doesThisTaskEquals.test(messageTransfer, myId) &&
//                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {
//
//
//
//            System.out.println(ServerHandler.threadName + "MESSAGE_SEND_DLG_LIST_BORROWED");
//            List<String> menu = lending.showBorrowedBooks(ShowSubjectItems.LONG_FORMAT);
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
