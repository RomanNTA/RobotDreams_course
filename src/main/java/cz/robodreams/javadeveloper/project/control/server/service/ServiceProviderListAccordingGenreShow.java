package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServiceProviderListAccordingGenreShow extends ServiceProviderImpl implements ServiceProvider {

    private Map<Book, List<String>> offerBooks;

    @Override
    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {

        myId = Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_SHOW;

        if (Const.doesThisTaskContain.test(messageTransfer, myId) &&
                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {

            String genre = messageTransfer.label();
            System.out.println(ServerHandler.threadName + myId + " - " + genre);


            offerBooks = article.showBooksAccordingToGenreForBuyin(genre);

            menu = offerBooks
                    .values()
                    .stream()
                    .flatMap(Collection::stream)
                    .toList();


//            menu.add("Zpět, bez výběru.");

//            genreMap = article.getListBooksAccordingGenre(Lock.LOCK);
//            genreMap.forEach((x, y) -> menu.add(x + " (" + y + ")"));




            return MessageTransfer.builder()
                    .task(Const.MESSAGES_SEND_MENU)
                    .replyTask(myId)
                    .label(Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_label)
                    .remark(Const.MESSAGE_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_text)
                    .menu(menu)
                    .output(-1)
                    .loop(false)
                    .build();

        } else {

            if (messageTransfer.output() == 0) {
                System.out.println(ServerHandler.threadName + " - " + myId + " Nevybral jsi nic.");
            } else {
                System.out.println(ServerHandler.threadName + " - " + myId + " - " + messageTransfer.output());
            }
        }
        return MessageTransfer.builder().task(Const.EMPTY).build();

    }

}
