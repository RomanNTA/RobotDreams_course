package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.List;

public class ServiceProviderClientLoanList extends ServiceProviderImpl implements ServiceProvider {

    public ServiceProviderClientLoanList() {
        myId = Const.CLIENT_SEND_DLG_LOAN_LIST;
    }

    @Override
    public MessageTransfer sendOffer() {

        System.out.println(ServerHandler.threadName + "CLIENT_SEND_DLG_LOAN_LIST");
        List<String> menu = lending.showBorrowedBooksFromUser(serverHandler.getClient());
        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .strInOut1(Const.CLIENT_SEND_DLG_LOAN_LIST_label)
                .strInOut2(Const.CLIENT_SEND_DLG_LOAN_LIST_text)
                .replyTask(Const.CLIENT_RETURN_TO_ROOT)
                .menu(menu)
                .loop(false)
                .build();
    }




//
//    @Override
//    public MessageTransfer processAnswer() {
//
//        return MessageTransfer.builder()
//                .task(Const.CLIENT_RETURN_TO_ROOT)
//                .loop(true)
//                .build();
//    }



//    @Override
//    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
//
//
//        System.out.println(ServerHandler.threadName + "CLIENT_SEND_DLG_LOAN_LIST - vstup");
//
//        if (Const.doesThisTaskEquals.test(messageTransfer, myId) &&
//                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {
//
//            System.out.println(ServerHandler.threadName + "CLIENT_SEND_DLG_LOAN_LIST");
//
//            List<String> menu = lending.showBorrowedBooksFromUser(serverHandler.getClient());
//
//            return MessageTransfer.builder()
//                    .task(Const.MESSAGES_PRINT_TEXT)
//                    .replyTask(Const.CLIENT_RETURN_TO_ROOT)
//                    .menu(menu)
//                    .loop(false)
//                    .build();
//
//        } else {
//            return MessageTransfer.builder().task(Const.CLIENT_RETURN_TO_ROOT).loop(true).build();
//        }
//        //return MessageTransfer.builder().task(Const.EMPTY).build();
//    }

}
