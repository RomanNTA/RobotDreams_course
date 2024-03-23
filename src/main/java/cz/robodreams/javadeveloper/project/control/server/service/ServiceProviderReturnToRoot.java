package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderReturnToRoot implements ServiceProvider {

    @Override
    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {

        if (Const.doesThisTaskContain.test(messageTransfer, Const.RETURN_TO_ROOT) &&
                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {

            System.out.println(ServerHandler.threadName + "RETURN_TO_ROOT");

            List<String> menu = new ArrayList<>();
            menu.add("Odhlášení/ukončení aplikace.");
            menu.add("Výpis všech knih");
            menu.add("Výpis půjčených knih");
            menu.add("Výpis uživatelů");
            menu.add("Nákup knih od dodavatele");

            return MessageTransfer.builder()
                    .task(Const.MESSAGES_SEND_MENU)
                    .replyTask(Const.RETURN_TO_ROOT)
                    .label(Const.MESSAGE_DLG2_ROOT_MENU_label)
                    .remark(Const.MESSAGE_DLG2_ROOT_MENU_text)
                    .menu(menu)
                    .output(-1)
                    .loop(false)
                    .build();

        } else {



            switch (messageTransfer.output()) {

                case 0 -> {
                    System.out.println(ServerHandler.threadName + "ServiceProviderReturnToRoot " + messageTransfer.output());
                    System.out.println(ServerHandler.threadName + "RETURN_TO_ROOT -> ukončení aplikace.");
                    return MessageTransfer.builder().task(Const.EXIT).loop(false).build();
                }
                case 1 -> {
                    System.out.println(ServerHandler.threadName + "ServiceProviderReturnToRoot " + messageTransfer.output());
                    return MessageTransfer.builder().task(Const.MESSAGE_SEND_DLG_LIST).loop(true).build();
                }
//                case 2 -> {
//                    return MessageTransfer.builder().task(Const.MESSAGE_SEND_DLG_BORROWED_BOOKS).build();
//                }
//                case 3 -> {
//                    return MessageTransfer.builder().task(Const.MESSAGE_SEND_DLG_ALL_USER).build();
//                }
                case 4 -> {
                    return MessageTransfer.builder().task(Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE).loop(true).build();
                }
            }


        }
        return MessageTransfer.builder().task(Const.EMPTY).build();

    }

}
