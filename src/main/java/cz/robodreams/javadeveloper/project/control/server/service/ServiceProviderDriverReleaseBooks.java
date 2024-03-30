package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;
import cz.robodreams.javadeveloper.project.lending.ILoan;
import cz.robodreams.javadeveloper.project.users.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static cz.robodreams.javadeveloper.project.control.common.Const.isNotNull;
import static cz.robodreams.javadeveloper.project.control.common.Const.testIndex;

public class ServiceProviderDriverReleaseBooks extends ServiceProviderImpl implements ServiceProvider {

    private static Map<Integer, ILoan> loanBooks;
    public ServiceProviderDriverReleaseBooks() {
        myId = Const.DRIVER_SEND_DLG_RELEASE_BOOKS;
    }


    @Override
    public MessageTransfer sendOffer() {

        Integer userId = messageTransfer.intResult();
        loanBooks = lending.driverReleaseBooks(userId);

        String name = loanBooks.values().stream()
                .map(ILoan::getUser)
                .map(User::getShortInfo)
                .findFirst()
                .get();

        menu = loanBooks.values().stream()
                .map(ILoan::getBook)
                .map(Book::getShortInfo)
                .toList();

        return MessageTransfer.builder()
                .task(Const.MESSAGES_SEND_MENU)
                .replyTask(myId)
                .strInOut1(Const.DRIVER_SEND_DLG_RELEASE_BOOKS_label)
                .strInOut2(String.format(Const.DRIVER_SEND_DLG_RELEASE_BOOKS_text,name.substring(2)))
                .menu(menu)
                .intResult(loanBooks.size())
                .loop(false)
                .build();

    }

    @Override
    public MessageTransfer processAnswer() {

        menu.clear();
        ILoan lol = loanBooks.values().stream().toList().get(messageTransfer.intResult());

        String strBook = lol.getBook().getShortInfo();
        String strUser = lol.getUser().getShortInfo().substring(2);

        menu.add( lending.removeLoan(lol) ? "OK -> " + strUser + " vrátil " + strBook  :  "Chyba -> " + strUser + " nelze vrátit " + strBook);

        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .replyTask(Const.DRIVER_RETURN_TO_ROOT)
                .menu(menu)
                .loop(false)
                .build();
    }
}
