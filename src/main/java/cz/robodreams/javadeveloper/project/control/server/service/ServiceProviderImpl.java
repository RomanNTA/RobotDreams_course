package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.ArticlesRepositoryImpl;
import cz.robodreams.javadeveloper.project.article.interfaces.ArticlesRepository;
import cz.robodreams.javadeveloper.project.common.Service;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;
import cz.robodreams.javadeveloper.project.lending.Lending;
import cz.robodreams.javadeveloper.project.users.Users;

import java.util.ArrayList;
import java.util.List;

public abstract class ServiceProviderImpl implements ServiceProvider {

    protected ArticlesRepository article = Service.getInstance().getArticle();
    protected Users users = Service.getInstance().getUser();
    protected Lending lending = Service.getInstance().getLending();
    protected List<String> menu = new ArrayList<>();
    protected String myId;

}
