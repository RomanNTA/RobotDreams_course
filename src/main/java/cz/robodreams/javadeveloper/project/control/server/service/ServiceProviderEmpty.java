package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.Role;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

public class ServiceProviderEmpty extends ServiceProviderImpl implements ServiceProvider {

    @Override
    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
        return messageTransfer;
    }

}
