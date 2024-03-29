package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderDriverReturnToRoot extends ServiceProviderImpl implements ServiceProvider {

    public ServiceProviderDriverReturnToRoot() {
        myId = Const.DRIVER_RETURN_TO_ROOT;
    }

    @Override
    public MessageTransfer sendOffer() {
        System.out.println(ServerHandler.threadName + myId);

        List<String> menu = new ArrayList<>();
        menu.add("Odhlášení/ukončení aplikace.");
        menu.add("Výpis všech knih");
        menu.add("Výpis půjčených knih");
        menu.add("Výpis uživatelů");
        menu.add("Nákup knih od dodavatele");
        menu.add("Vrácení knih");


        return MessageTransfer.builder()
                .task(Const.MESSAGES_SEND_MENU)
                .replyTask(myId)
                .strInOut1(Const.DRIVER_DLG2_ROOT_MENU_label)
                .strInOut2(Const.DRIVER_DLG2_ROOT_MENU_text)
                .menu(menu)
                .intResult(-1)
                .loop(false)
                .build();
    }

    @Override
    public MessageTransfer processAnswer() {

        switch (messageTransfer.intResult()) {

            case 0 -> {
                System.out.println(ServerHandler.threadName + "ServiceProviderDriverReturnToRoot " + messageTransfer.intResult());
                System.out.println(ServerHandler.threadName + "RETURN_TO_ROOT -> ukončení aplikace.");
                return MessageTransfer.builder().task(Const.EXIT).loop(false).build();
            }
            case 1 -> {
                System.out.println(ServerHandler.threadName + "ServiceProviderDriverReturnToRoot " + messageTransfer.intResult());
                return MessageTransfer.builder().task(Const.DRIVER_SEND_DLG_LIST).loop(true).build();
            }
            case 2 -> {
                System.out.println(ServerHandler.threadName + "ServiceProviderDriverListArticleBorrowed " + messageTransfer.intResult());
                return MessageTransfer.builder().task(Const.DRIVER_SEND_DLG_LIST_BORROWED).loop(true).build();
            }
            case 3 -> {
                System.out.println(ServerHandler.threadName + "ServiceProviderDriverListAllUsers " + messageTransfer.intResult());
                return MessageTransfer.builder().task(Const.DRIVER_SEND_DLG_LIST_ALL_USERS).loop(true).build();
            }
            case 4 -> {
                return MessageTransfer.builder().task(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE).loop(true).build();
            }
            case 5 -> {
                return MessageTransfer.builder().task(Const.DRIVER_SEND_DLG_RELEASE_CLIENT_SELECTION).loop(true).build();
            }
        }
        return MessageTransfer.builder().task(Const.EMPTY).build();
    }
}


//
//    @Override
//    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
//
//
//        if (Const.doesThisTaskEquals.test(messageTransfer, myId) &&
//                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {
//
//            System.out.println(ServerHandler.threadName + myId);
//
//            List<String> menu = new ArrayList<>();
//            menu.add("Odhlášení/ukončení aplikace.");
//            menu.add("Výpis všech knih");
//            menu.add("Výpis půjčených knih");
//            menu.add("Výpis uživatelů");
//            menu.add("Nákup knih od dodavatele");
//
//            return MessageTransfer.builder()
//                    .task(Const.MESSAGES_SEND_MENU)
//                    .replyTask(myId)
//                    .strInOut1(Const.DRIVER_DLG2_ROOT_MENU_label)
//                    .strInOut2(Const.DRIVER_DLG2_ROOT_MENU_text)
//                    .menu(menu)
//                    .intResult(-1)
//                    .loop(false)
//                    .build();
//
//        } else {
//
//
//            switch (messageTransfer.intResult()) {
//
//                case 0 -> {
//                    System.out.println(ServerHandler.threadName + "ServiceProviderDriverReturnToRoot " + messageTransfer.intResult());
//                    System.out.println(ServerHandler.threadName + "RETURN_TO_ROOT -> ukončení aplikace.");
//                    return MessageTransfer.builder().task(Const.EXIT).loop(false).build();
//                }
//                case 1 -> {
//                    System.out.println(ServerHandler.threadName + "ServiceProviderDriverReturnToRoot " + messageTransfer.intResult());
//                    return MessageTransfer.builder().task(Const.DRIVER_SEND_DLG_LIST).loop(true).build();
//                }
//                case 2 -> {
//                    System.out.println(ServerHandler.threadName + "ServiceProviderDriverListArticleBorrowed " + messageTransfer.intResult());
//                    return MessageTransfer.builder().task(Const.DRIVER_SEND_DLG_LIST_BORROWED).loop(true).build();
//                }
//                case 3 -> {
//                    System.out.println(ServerHandler.threadName + "ServiceProviderDriverListAllUsers " + messageTransfer.intResult());
//                    return MessageTransfer.builder().task(Const.DRIVER_SEND_DLG_LIST_ALL_USERS).loop(true).build();
//                }
//                case 4 -> {
//                    return MessageTransfer.builder().task(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE).loop(true).build();
//                }
//            }
//
//
//        }
//        return MessageTransfer.builder().task(Const.EMPTY).build();
//
//    }
//
//}
