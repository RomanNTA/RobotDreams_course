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

        menu = article.getListBook(Lock.UNLOCK, ArticleType.BOOKS);

        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .replyTask(Const.DRIVER_RETURN_TO_ROOT)
                .strInOut1(Const.DRIVER_SEND_DLG_LIST_BORROWED_label)
                .strInOut2(Const.DRIVER_SEND_DLG_LIST_BORROWED_text)
                .menu(menu)
                .loop(false)
                .build();

    }
}
