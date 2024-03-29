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

        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .strInOut1(Const.DRIVER_SEND_DLG_LIST_BORROWED_label)
                .strInOut2(Const.DRIVER_SEND_DLG_LIST_BORROWED_text)
                .replyTask(Const.DRIVER_RETURN_TO_ROOT)
                .menu(lending.showBorrowedBooks(ShowSubjectItems.LONG_FORMAT))
                .loop(false)
                .build();
    }

}
