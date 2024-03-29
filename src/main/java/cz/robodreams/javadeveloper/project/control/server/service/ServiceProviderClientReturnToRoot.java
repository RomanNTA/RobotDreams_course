package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.ArrayList;
import java.util.List;

import static cz.robodreams.javadeveloper.project.control.common.Const.isNotNull;

public class ServiceProviderClientReturnToRoot extends ServiceProviderImpl implements ServiceProvider {

    public ServiceProviderClientReturnToRoot() {
        myId = Const.CLIENT_RETURN_TO_ROOT;
    }

    @Override
    public MessageTransfer sendOffer() {

        menu.add("Odhlášení/ukončení aplikace.");
        menu.add("Výběr registrovaného uživatele.");

        if (isNotNull.test( serverHandler.getClient())) {
            menu.add("Výpis půjčených knih.");
            menu.add("Výběr volných knih a potvrzení půjčení.");
        }

        return MessageTransfer.builder()
                .task(Const.MESSAGES_SEND_MENU)
                .replyTask(myId)
                .strInOut1(Const.CLIENT_DLG2_ROOT_MENU_label + serverHandler.getUserName())
                .strInOut2(Const.CLIENT_DLG2_ROOT_MENU_text)
                .menu(menu)
                .intResult(-1)
                .loop(false)
                .build();
    }



    @Override
    public MessageTransfer processAnswer() {

        switch (messageTransfer.intResult()) {

            case 0 -> {
                return MessageTransfer.builder().task(Const.EXIT).loop(false).build();
            }
            case 1 -> {
                return MessageTransfer.builder().task(Const.CLIENT_SEND_DLG_LOGIN_USER).loop(true).build();
            }
            case 2 -> {
                return MessageTransfer.builder().task(Const.CLIENT_SEND_DLG_LOAN_LIST).loop(true).build();
            }
            case 3 -> {
                return MessageTransfer.builder().task(Const.CLIENT_SEND_DLG_LOAN_BOOKS).loop(true).build();
            }
        }
        return MessageTransfer.builder().task(Const.EMPTY).build();
    }
}
