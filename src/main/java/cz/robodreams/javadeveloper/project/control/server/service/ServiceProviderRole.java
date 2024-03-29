package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.Role;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

public class ServiceProviderRole extends ServiceProviderImpl implements ServiceProvider {

    public ServiceProviderRole() {
        myId = Const.MESSAGES_FIRST_CONNECT;
    }

    @Override
    public MessageTransfer sendOffer() {
        System.out.println(ServerHandler.threadName + myId);

        List<String> menu = new ArrayList<>();
        menu.add("Knihovnice");
        menu.add("Klient knihovny");

        return MessageTransfer.builder().task(Const.MESSAGES_SEND_MENU).replyTask(myId).strInOut1(Const.DRIVER_DLG2_WHAT_ROLE_label).strInOut2(Const.DRIVER_DLG2_WHAT_ROLE_text).menu(menu).intResult(-1).loop(false).build();
    }

    @Override
    public MessageTransfer processAnswer() {
        System.out.println(ServerHandler.threadName + "ServiceProviderRole 2 část");

        switch (messageTransfer.intResult()) {
            case 0 -> {
                serverHandler.setRole(Role.DRIVER);
                return MessageTransfer.builder().task(Const.DRIVER_RETURN_TO_ROOT).loop(true).build();
            }
            case 1 -> {
                serverHandler.setRole(Role.CLIENT);
                return MessageTransfer.builder().task(Const.CLIENT_RETURN_TO_ROOT).loop(true).build();
            }
            default -> throw new ResolutionException("Nedefinovaná role. ");
        }
    }
}

//
//    @Override
//    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
//
//
//        if (messageTransfer.replyTask().isEmpty()) {
//
//            System.out.println(ServerHandler.threadName + myId);
//
//            List<String> menu = new ArrayList<>();
//            menu.add("Knihovnice");
//            menu.add("Klient knihovny");
//
//            return MessageTransfer.builder()
//                    .task(Const.MESSAGES_SEND_MENU)
//                    .replyTask(myId)
//                    .strInOut1(Const.DRIVER_DLG2_WHAT_ROLE_label)
//                    .strInOut2(Const.DRIVER_DLG2_WHAT_ROLE_text)
//                    .menu(menu)
//                    .intResult(-1)
//                    .loop(false)
//                    .build();
//        } else {
//
//            System.out.println(ServerHandler.threadName + "ServiceProviderRole 2 část");
//
//            switch (messageTransfer.intResult()) {
//                case 0 -> {
//                    serverHandler.setRole(Role.DRIVER);
//                    return MessageTransfer.builder().task(Const.DRIVER_RETURN_TO_ROOT).loop(true).build();
//                }
//                case 1 -> {
//                    serverHandler.setRole(Role.CLIENT);
//                    return MessageTransfer.builder().task(Const.CLIENT_RETURN_TO_ROOT).loop(true).build();
//                }
//                default -> throw new ResolutionException("Nedefinovaná role. ");
//            }
//
//        }
//
//
//    }
//
//}
