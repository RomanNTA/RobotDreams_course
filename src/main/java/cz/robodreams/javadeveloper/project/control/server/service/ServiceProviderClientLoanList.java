package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;

public class ServiceProviderClientLoanList extends ServiceProviderImpl implements ServiceProvider {

    public ServiceProviderClientLoanList() {
        myId = Const.CLIENT_SEND_DLG_LOAN_LIST;
    }

    @Override
    public MessageTransfer sendOffer() {

        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .strInOut1(Const.CLIENT_SEND_DLG_LOAN_LIST_label)
                .strInOut2(Const.CLIENT_SEND_DLG_LOAN_LIST_text)
                .replyTask(Const.CLIENT_RETURN_TO_ROOT)
                .menu(lending.showBorrowedBooksFromUser(serverHandler.getClient()))
                .loop(false)
                .build();
    }

}
