package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

public interface ServiceProvider {

    MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler);
    MessageTransfer sendOffer();
    MessageTransfer processAnswer();


}
