package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.Role;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

public class ServiceProviderRole implements ServiceProvider {

    @Override
    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {

        if (Const.doesThisTaskContain.test(messageTransfer,Const.MESSAGES_FIRST_CONNECT) &&
                messageTransfer.replyTask().isEmpty()) {

            System.out.println(ServerHandler.threadName + "ServiceProviderRole 1 část");

            List<String> menu = new ArrayList<>();
            menu.add("Knihovnice");
            menu.add("Klient knihovny");

            return MessageTransfer.builder()
                    .task(Const.MESSAGES_SEND_MENU)
                    .replyTask(Const.MESSAGES_FIRST_CONNECT)
                    .label(Const.MESSAGE_DLG2_WHAT_ROLE_label)
                    .remark(Const.MESSAGE_DLG2_WHAT_ROLE_text)
                    .menu(menu)
                    .output(-1)
                    .loop(false)
                    .build();
        } else {

            System.out.println(ServerHandler.threadName + "ServiceProviderRole 2 část");

            switch (messageTransfer.output()) {
                case 0 -> serverHandler.setRole(Role.DRIVER);
                case 1 -> serverHandler.setRole(Role.CLIENT);
                default -> throw new ResolutionException("Nedefinovaná role. ");
            }
            return MessageTransfer.builder().task(Const.RETURN_TO_ROOT).loop(true).build();
        }


    }

}
