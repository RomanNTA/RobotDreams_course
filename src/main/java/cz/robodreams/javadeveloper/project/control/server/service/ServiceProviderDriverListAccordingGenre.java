package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.HashMap;
import java.util.Map;

public class ServiceProviderDriverListAccordingGenre extends ServiceProviderImpl implements ServiceProvider {

    private static Map<String, Long> genreMap = new HashMap<>();

    public ServiceProviderDriverListAccordingGenre() {
        myId = Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE;
    }

    @Override
    public MessageTransfer sendOffer() {
        System.out.println(ServerHandler.threadName + myId);

        menu.clear();
        menu.add("Zpět, bez výběru.");

        genreMap.clear();
//        System.out.println(ServerHandler.threadName + " -  genreMap.size() " + genreMap.size());
//        System.out.println(ServerHandler.threadName + " -      menu.size() " + menu.size());

        genreMap = article.getListBooksAccordingGenre(Lock.LOCK);
        genreMap.forEach((x, y) -> menu.add(x + " (" + y + ")"));

//        System.out.println(ServerHandler.threadName + " -  genreMap.size() " + genreMap.size());
//        System.out.println(ServerHandler.threadName + " -      menu.size() " + menu.size());


        return MessageTransfer.builder()
                .task(Const.MESSAGES_SEND_MENU)
                .replyTask(myId)
                .strInOut1(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_label)
                .strInOut2(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_text)
                .menu(menu)
                .intResult(-1)
                .loop(false)
                .build();


    }

    @Override
    public MessageTransfer processAnswer() {

        if (messageTransfer.intResult() == 0) {

            System.out.println(ServerHandler.threadName + " - " + myId + " Nevybral jsi nic.");
            return MessageTransfer.builder()
                    .task(Const.DRIVER_RETURN_TO_ROOT)
                    .loop(true)
                    .build();

        } else {

            return MessageTransfer.builder()
                    .task(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW)
                    .strInOut1(genreMap.keySet().stream().toList().get(messageTransfer.intResult() - 1))
                    .loop(true)
                    .build();
        }
    }
}

//
//    @Override
//    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
//
//        myId = Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE;
//
//        if (Const.doesThisTaskEquals.test(messageTransfer, myId) &&
//                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {
//
//            System.out.println(ServerHandler.threadName + myId);
//
//            menu.clear();
//            menu.add("Zpět, bez výběru.");
//
//            genreMap.clear();
//            System.out.println(ServerHandler.threadName + " -  genreMap.size() " + genreMap.size());
//            System.out.println(ServerHandler.threadName + " -      menu.size() " + menu.size());
//
//            genreMap = article.getListBooksAccordingGenre(Lock.LOCK);
//            genreMap.forEach((x, y) -> menu.add(x + " (" + y + ")"));
//
//            System.out.println(ServerHandler.threadName + " -  genreMap.size() " + genreMap.size());
//            System.out.println(ServerHandler.threadName + " -      menu.size() " + menu.size());
//
//
//            return MessageTransfer.builder()
//                    .task(Const.MESSAGES_SEND_MENU)
//                    .replyTask(myId)
//                    .strInOut1(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_label)
//                    .strInOut2(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_text)
//                    .menu(menu)
//                    .intResult(-1)
//                    .loop(false)
//                    .build();
//
//        } else {
//
//            if (messageTransfer.intResult() == 0) {
//
//                System.out.println(ServerHandler.threadName + " - " + myId + " Nevybral jsi nic.");
//                return MessageTransfer.builder()
//                        .task(Const.DRIVER_RETURN_TO_ROOT)
//                        .loop(true)
//                        .build();
//
//            } else {
//
//                return MessageTransfer.builder()
//                        .task(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW)
//                        .strInOut1(genreMap.keySet().stream().toList().get(messageTransfer.intResult() - 1))
//                        .loop(true)
//                        .build();
//            }
//        }
//    }

