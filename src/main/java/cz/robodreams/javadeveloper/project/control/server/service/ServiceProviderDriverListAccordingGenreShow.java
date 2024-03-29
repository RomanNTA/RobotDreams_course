package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Lock;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static cz.robodreams.javadeveloper.project.control.common.Const.isNotNull;
import static cz.robodreams.javadeveloper.project.control.common.Const.testIndex;

public class ServiceProviderDriverListAccordingGenreShow extends ServiceProviderImpl implements ServiceProvider {

    private static Map<Integer, Book> offerBooks;

    public ServiceProviderDriverListAccordingGenreShow() {
        myId = Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW;
    }

    @Override
    public MessageTransfer sendOffer() {

        String genre = messageTransfer.strInOut1();
        offerBooks = article.showBooksAccordingToGenreForBuyin(genre);
        AtomicInteger counter = new AtomicInteger(1);

        menu.addAll(offerBooks.entrySet().stream()
                .map(x -> ((Book) x.getValue()).getResultShow(ShowSubjectItems.INFO))
                .map(x -> (List<String>) x)
                .peek(line -> line.add(Util.getLine()))
                .peek(row -> row.add("  Kniha " + counter.getAndIncrement() + "\n"))
                .flatMap(Collection::stream)
                .toList());

        menu.add(Util.getLine());

        menu.add("Výběr knihy. Povolené zadání je:");
        menu.add(" - návrat bez výběru tj. číslo '0'");
        menu.add(" - číslo knihy např. '1'");
        menu.add(" - rozsah např.  knihy v pořadí '3-6'");
        menu.add(" - vše tj. písmeno 'A'");

        return MessageTransfer.builder()
                .task(Const.MESSAGES_SEND_MENU_CHOICE_BOOKS)
                .replyTask(myId)
                .strInOut1(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_label)
                .strInOut2(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_text)
                .menu(menu)
                .intResult(offerBooks.size())
                .loop(false)
                .build();

    }

    @Override
    public MessageTransfer processAnswer() {

        menu.clear();
        while (true) {

            if (isNotNull.test(messageTransfer.intResult()) && messageTransfer.intResult() == 0) {
                return MessageTransfer.builder()
                        .task(Const.DRIVER_RETURN_TO_ROOT)
                        .loop(true)
                        .build();
            }

            // jednu knihu
            if (isNotNull.test(messageTransfer.intResult()) && messageTransfer.intResult() > 0) {
                if (!testIndex.test(messageTransfer.intResult(), offerBooks.size() + 1)) {
                    throw new RuntimeException("Výměr mimo povolený rozsah");
                }
                offerBooks.get(messageTransfer.intResult()).setLocked(Lock.UNLOCK);
                menu.add("Koupě: 1 knihy.");
                break;
            }

            // všechny knihy
            if (isNotNull.test(messageTransfer.strInOut1()) && messageTransfer.strInOut1().equalsIgnoreCase("A")) {
                offerBooks.values().forEach(x -> x.setLocked(Lock.UNLOCK));
                menu.add("Koupě: " + offerBooks.size() + " knih.");
                break;
            }


            // rozsah od - do
            if (Objects.nonNull(messageTransfer.strInOut1()) && messageTransfer.strInOut1().length() > 0) {

                String[] inp = messageTransfer.strInOut1().split(";");

                int count = 0;
                for (int i = 0; i < inp.length; i++) {
                    try {
                        int i1 = Integer.valueOf(inp[i].trim());
                        if (i1 > 0 && i1 <= offerBooks.size()) {
                            offerBooks.get(i1).setLocked(Lock.UNLOCK);
                        }
                        count++;
                    } catch (NumberFormatException e) {
                        System.out.println("Chybný převod vstupního zadání 3.");
                    }
                }
                menu.add("Koupě: " + count + " knih.");
                break;
            }
        }
        return MessageTransfer.builder()
                .task(Const.MESSAGES_PRINT_TEXT)
                .replyTask(Const.DRIVER_RETURN_TO_ROOT)
                .menu(menu)
                .loop(false)
                .build();
    }
}

