package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceProviderListAccordingGenre extends ServiceProviderImpl implements ServiceProvider {

    private Map<String, Long> genreMap = new HashMap<>();

    @Override
    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {

        myId = Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE;

        if (Const.doesThisTaskContain.test(messageTransfer, myId) &&
                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {

            System.out.println(ServerHandler.threadName + myId);

            menu.add("Zpět, bez výběru.");

            genreMap = article.getListBooksAccordingGenre(Lock.LOCK);
            genreMap.forEach((x, y) -> menu.add(x + " (" + y + ")"));

            return MessageTransfer.builder()
                    .task(Const.MESSAGES_SEND_MENU)
                    .replyTask(myId)
                    .label(Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_label)
                    .remark(Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_text)
                    .menu(menu)
                    .output(-1)
                    .loop(false)
                    .build();

        } else {

            if (messageTransfer.output() == 0) {

                System.out.println(ServerHandler.threadName + " - " + myId + " Nevybral jsi nic.");
                return MessageTransfer.builder()
                        .task(Const.RETURN_TO_ROOT)
                        .loop(true)
                        .build();

            } else {

                return MessageTransfer.builder()
                        .task(Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_SHOW)
                        .label(genreMap.keySet().stream().toList().get(messageTransfer.output() - 1))
                        .loop(true)
                        .build();
            }
        }
    }

}
