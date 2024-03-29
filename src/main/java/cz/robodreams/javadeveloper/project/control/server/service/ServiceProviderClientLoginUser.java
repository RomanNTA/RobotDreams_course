package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.Role;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;
import cz.robodreams.javadeveloper.project.users.User;
import org.apache.commons.collections4.map.HashedMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static cz.robodreams.javadeveloper.project.control.common.Const.*;

public class ServiceProviderClientLoginUser extends ServiceProviderImpl implements ServiceProvider {

    private static Map<Integer, User> regUser = new HashedMap<>();

    public ServiceProviderClientLoginUser() {
        myId = CLIENT_SEND_DLG_LOGIN_USER;
    }


    @Override
    public MessageTransfer sendOffer() {

        regUser = users.getUsersClient();

        menu.addAll(regUser.values().stream()
                .map(x -> ((User) x).getRegisteredClient())
                .toList());

        return MessageTransfer.builder()
                .task(MESSAGES_SEND_MENU)
                .strInOut1(CLIENT_SEND_DLG_LOGIN_USER_label)
                .strInOut2(CLIENT_SEND_DLG_LOGIN_USER_text)
                .replyTask(myId)
                .menu(menu)
                .loop(false)
                .build();
    }

    @Override
    public MessageTransfer processAnswer() {

        serverHandler.setClient(regUser.get(messageTransfer.intResult()));

        return MessageTransfer.builder()
                .task(Const.CLIENT_RETURN_TO_ROOT)
                .loop(true)
                .build();
    }

}
