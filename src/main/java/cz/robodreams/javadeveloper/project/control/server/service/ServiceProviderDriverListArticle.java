package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.ArticlesRepositoryImpl;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.List;

public class ServiceProviderDriverListArticle extends ServiceProviderImpl implements ServiceProvider {

    public ServiceProviderDriverListArticle() {
        myId = Const.DRIVER_SEND_DLG_LIST;
    }

    @Override
    public MessageTransfer sendOffer() {

        System.out.println(ServerHandler.threadName + myId);

        Service service = Service.getInstance();
        List<String> menu = ((ArticlesRepositoryImpl) service.getArticle()).getListBook(Lock.UNLOCK, ArticleType.BOOKS);

        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .replyTask(Const.DRIVER_RETURN_TO_ROOT)
                .menu(menu)
                .loop(false)
                .build();

    }

//    @Override
//    public MessageTransfer processAnswer() {
//
//    }
//
//    @Override
//    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
//
//
//
//        if (Const.doesThisTaskEquals.test(messageTransfer, myId) &&
//                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {
//
//            System.out.println(ServerHandler.threadName + "MESSAGE_SEND_DLG_LIST");
//
//            Service service = Service.getInstance();
//            List<String> menu = ((ArticlesRepositoryImpl) service.getArticle()).getListBook(Lock.UNLOCK, ArticleType.BOOKS);
//
//            return MessageTransfer.builder()
//                    .task(Const.MESSAGES_PRINT_TEXT)
//                    .replyTask(Const.DRIVER_RETURN_TO_ROOT)
//                    .menu(menu)
//                    .loop(false)
//                    .build();
//
//        } else {
//            return MessageTransfer.builder().task(Const.DRIVER_RETURN_TO_ROOT).loop(true).build();
//        }
//        //return MessageTransfer.builder().task(Const.EMPTY).build();
//    }

}
