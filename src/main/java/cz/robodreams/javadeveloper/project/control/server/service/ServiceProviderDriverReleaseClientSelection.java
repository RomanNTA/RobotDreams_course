package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;
import cz.robodreams.javadeveloper.project.users.User;

import java.util.Map;

public class ServiceProviderDriverReleaseClientSelection extends ServiceProviderImpl implements ServiceProvider {

    private static Map<User, Long> allUserWithLoan;

    public ServiceProviderDriverReleaseClientSelection() {
        myId = Const.DRIVER_SEND_DLG_RELEASE_CLIENT_SELECTION;
    }

    @Override
    public MessageTransfer sendOffer() {

        allUserWithLoan = lending.driverReleaseClientSelection();

        menu.addAll(allUserWithLoan.entrySet().stream()
                .map((x) -> (x.getKey().getShortInfo().substring(2) + "(" + Util.colCyan(x.getValue().toString()) + ")"))
                .toList());

        return MessageTransfer.builder()
                .task(Const.MESSAGES_SEND_MENU)
                .replyTask(myId)
                .strInOut1(Const.DRIVER_SEND_DLG_RELEASE_CLIENT_SELECTION_label)
                .strInOut2(Const.DRIVER_SEND_DLG_RELEASE_CLIENT_SELECTION_text)
                .menu(menu)
                .intResult(allUserWithLoan.size())
                .loop(false)
                .build();

    }

    @Override
    public MessageTransfer processAnswer() {

        int userId = allUserWithLoan.keySet().stream().toList().get(messageTransfer.intResult()).getId();

        return MessageTransfer.builder()
                .task(Const.DRIVER_SEND_DLG_RELEASE_BOOKS)
                .intResult(userId)
                .loop(true)
                .build();
    }
}
