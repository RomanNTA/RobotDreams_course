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

        List<String> menu = users.getUsersDriver(ShowSubjectItems.LONG_FORMAT);
        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .replyTask(Const.DRIVER_RETURN_TO_ROOT)
                .strInOut1(Const.DRIVER_SEND_DLG_LIST_ALL_USERS_label)
                .strInOut2(Const.DRIVER_SEND_DLG_LIST_ALL_USERS_text)
                .menu(menu)
                .loop(false)
                .build();

    }
}
