package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.ArticlesRepositoryImpl;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.ArticleType;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderListArticle implements ServiceProvider {

    @Override
    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {

        if (Const.doesThisTaskContain.test(messageTransfer, Const.MESSAGE_SEND_DLG_LIST) &&
                !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {

            System.out.println(ServerHandler.threadName + "MESSAGE_SEND_DLG_LIST");

            Service service = Service.getInstance();





//
//
//
//
//            List<Book> menu = new ArrayList<>();
//
//            books
//
//            menu.addAll(service.getArticle().getList(Lock.UNLOCK, ArticleType.BOOKS));
//

            List<String> menu = ((ArticlesRepositoryImpl) service.getArticle()).getListBook(Lock.UNLOCK, ArticleType.BOOKS);
//            System.out.println(ServerHandler.threadName + "-------------------------------");
//            menu.forEach(System.out::println);
//            System.out.println(ServerHandler.threadName + "-------------------------------");
//
//
//
//            //System.out.println(ServerHandler.threadName + "MESSAGE_SEND_DLG_LIST menu " + menu.size())));
//            System.out.println(ServerHandler.threadName + "MESSAGE_SEND_DLG_LIST menu " +
//                    ((ArticlesRepositoryImpl) service.getArticle()).getListBook(Lock.UNLOCK, ArticleType.BOOKS).size()
//            );

            return MessageTransfer.builder()
                    .task(Const.MESSAGES_PRINT_TEXT)
                    .replyTask(Const.RETURN_TO_ROOT)
//                    .label()
//                    .remark()
                    .menu(menu)
//                    .output(-1)
                    .loop(false)
                    .build();

        } else {
            return MessageTransfer.builder().task(Const.RETURN_TO_ROOT).loop(true).build();
        }
        //return MessageTransfer.builder().task(Const.EMPTY).build();
    }

}
