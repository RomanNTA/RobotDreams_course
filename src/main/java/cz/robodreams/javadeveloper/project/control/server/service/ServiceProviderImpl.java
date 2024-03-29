package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.ArticlesRepositoryImpl;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.Role;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;
import cz.robodreams.javadeveloper.project.lending.ILoan;
import cz.robodreams.javadeveloper.project.lending.Lending;
import cz.robodreams.javadeveloper.project.users.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class ServiceProviderImpl implements ServiceProvider {

    protected ArticlesRepository article = Service.getInstance().getArticle();
    protected Users users = Service.getInstance().getUser();
    protected Lending<ILoan> lending = Service.getInstance().getLending();
    protected List<String> menu = new ArrayList<>();
    protected String myId;

    protected ServerHandler serverHandler;
    protected MessageTransfer messageTransfer;

    //    BiPredicate<Object,Object> testResultAndNotNull = (t,u) -> ((t != null) && (t == u));

    @Override
    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {

        this.messageTransfer = messageTransfer;
        this.serverHandler = serverHandler;
        return (Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) ?
                processAnswer() : sendOffer();
    }

    @Override
    public MessageTransfer sendOffer() {
        return null;
    }

    @Override
    public MessageTransfer processAnswer() {

        if (serverHandler.getRole() == Role.DRIVER) {

            return MessageTransfer.builder()
                    .task(Const.DRIVER_RETURN_TO_ROOT)
                    .loop(true)
                    .build();
        } else {

            return MessageTransfer.builder()
                    .task(Const.CLIENT_RETURN_TO_ROOT)
                    .loop(true)
                    .build();
        }
    }


}