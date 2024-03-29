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

        System.out.println(ServerHandler.threadName + " offerBooks.size = " + offerBooks.size());

        AtomicInteger counter = new AtomicInteger(1);

        List<String> tmpList = new ArrayList<>();
        tmpList.addAll(offerBooks.entrySet().stream()
                .map(x -> ((Book) x.getValue()).getResultShow(ShowSubjectItems.INFO))
                .map(x -> (List<String>) x)
                .peek(line -> line.add(Util.getLine()))
                .peek(row -> row.add("  Kniha " + counter.getAndIncrement() + "\n"))
                .flatMap(Collection::stream)
                .toList());
        tmpList.add(Util.getLine());
        tmpList.add("Výběr knihy. Povolené zadání je:");
        tmpList.add(" - návrat bez výběru tj. číslo '0'");
        tmpList.add(" - číslo knihy např. '1'");
        tmpList.add(" - rozsah např.  knihy v pořadí '3-6'");
        tmpList.add(" - vše tj. písmeno 'A'");

        return MessageTransfer.builder()
                .task(Const.MESSAGES_SEND_MENU_CHOICE_BOOKS)
                .replyTask(myId)
                .strInOut1(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_label)
                .strInOut2(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_text)
                .menu(tmpList)
                .intResult(offerBooks.size())
                .loop(false)
                .build();

    }

    @Override
    public MessageTransfer processAnswer() {
        //List<Book> unlockBook = new ArrayList<>();
        menu.clear();
        while (true) {

            System.out.println(ServerHandler.threadName + " - " + myId + " Příjem " + messageTransfer.toString());
            if (isNotNull.test(messageTransfer.intResult()) && messageTransfer.intResult() == 0) {
                System.out.println("Návrat bez koupě ");
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
                System.out.println("Kniha - " + offerBooks.get(messageTransfer.intResult()).getTitle());
                offerBooks.get(messageTransfer.intResult()).setLocked(Lock.UNLOCK);

                menu.add("Koupě: 1 knih.");
                break;
            }

            // všechny knihy
            if (isNotNull.test(messageTransfer.strInOut1()) && messageTransfer.strInOut1().equalsIgnoreCase("A")) {
                System.out.println("Koupě všech knih ");

                offerBooks.values().forEach(x -> System.out.println(x.getTitle()));
                offerBooks.values().forEach(x -> x.setLocked(Lock.UNLOCK));

                menu.add("Koupě: " + offerBooks.size() + " knih.");
                break;
            }


            // rozsah od - do
            if (Objects.nonNull(messageTransfer.strInOut1()) && messageTransfer.strInOut1().length() > 0) {
                String[] inp = messageTransfer.strInOut1().split(";");

                System.out.println("inp <- ");
                Arrays.stream(inp).toList().forEach(System.out::println);

                int count = 0;
                for (int i = 0; i < inp.length; i++) {
                    try {
                        int i1 = Integer.valueOf(inp[i].trim());
                        if (i1 > 0 && i1 <= offerBooks.size()) {
                            System.out.println("Kniha - " + inp[i] + " -> " + i1 + " " + offerBooks.get(i1).getTitle());
                            offerBooks.get(i1).setLocked(Lock.UNLOCK);
                        }
                        count++;
                    } catch (NumberFormatException e) {
                        System.out.println("Chybný převod vstupního zadání 3.");
                    }
                }
                menu.add("Koupě: " + count + " knih.");
                System.out.println(ServerHandler.threadName + " - " + myId + messageTransfer.toString());
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
    //return MessageTransfer.builder().task(Const.EMPTY).build();
}





//
//    @Override
//    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
//
//        myId = Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW;
////        if (!Objects.nonNull(messageTransfer.intResult())) {
////            throw new RuntimeException("Chyba: MessageTransfer je NULL !");
////        };
//
//        if (Const.doesThisTaskEquals.test(messageTransfer, myId) && !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {
//            String genre = messageTransfer.strInOut1();
//
//            offerBooks = article.showBooksAccordingToGenreForBuyin(genre);
//            System.out.println(ServerHandler.threadName + " offerBooks.size = " + offerBooks.size());
//
//            AtomicInteger counter = new AtomicInteger(1);
//
//            List<String> tmpList = new ArrayList<>();
//            tmpList.addAll(offerBooks.entrySet().stream()
//                    .map(x -> ((Book) x.getValue()).getResultShow(ShowSubjectItems.INFO))
//                    .map(x -> (List<String>) x)
//                    .peek(line -> line.add(Util.getLine()))
//                    .peek(row -> row.add("  Kniha " + counter.getAndIncrement() + "\n"))
//                    .flatMap(Collection::stream)
//                    .toList());
//            tmpList.add(Util.getLine());
//            tmpList.add("Výběr knihy. Povolené zadání je:");
//            tmpList.add(" - návrat bez výběru tj. číslo '0'");
//            tmpList.add(" - číslo knihy např. '1'");
//            tmpList.add(" - rozsah např.  knihy v pořadí '3-6'");
//            tmpList.add(" - vše tj. písmeno 'A'");
//
//            return MessageTransfer.builder()
//                    .task(Const.MESSAGES_SEND_MENU_CHOICE_BOOKS)
//                    .replyTask(myId)
//                    .strInOut1(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_label)
//                    .strInOut2(Const.DRIVER_SEND_DLG_LIST_ACCORDING_GENRE_SHOW_text)
//                    .menu(tmpList)
//                    .intResult(offerBooks.size())
//                    .loop(false)
//                    .build();
//
//        } else {
//
//            //List<Book> unlockBook = new ArrayList<>();
//            menu.clear();
//            while (true) {
//
//                System.out.println(ServerHandler.threadName + " - " + myId + " Příjem " + messageTransfer.toString());
//                if (isNotNull.test(messageTransfer.intResult()) && messageTransfer.intResult() == 0) {
//                    System.out.println("Návrat bez koupě ");
//                    return MessageTransfer.builder()
//                            .task(Const.DRIVER_RETURN_TO_ROOT)
//                            .loop(true)
//                            .build();
//                }
//
//                // jednu knihu
//                if (isNotNull.test(messageTransfer.intResult()) && messageTransfer.intResult() > 0) {
//                    if (!testIndex.test(messageTransfer.intResult(), offerBooks.size() + 1)) {
//                        throw new RuntimeException("Výměr mimo povolený rozsah");
//                    }
//                    System.out.println("Kniha - " + offerBooks.get(messageTransfer.intResult()).getTitle());
//                    offerBooks.get(messageTransfer.intResult()).setLocked(Lock.UNLOCK);
//
//                    menu.add("Koupě: 1 knih.");
//                    break;
//                }
//
//                // všechny knihy
//                if (isNotNull.test(messageTransfer.strInOut1()) && messageTransfer.strInOut1().equalsIgnoreCase("A")) {
//                    System.out.println("Koupě všech knih ");
//
//                    offerBooks.values().forEach(x -> System.out.println(x.getTitle()));
//                    offerBooks.values().forEach(x -> x.setLocked(Lock.UNLOCK));
//
//                    menu.add("Koupě: " + offerBooks.size() + " knih.");
//                    break;
//                }
//
//
//                // rozsah od - do
//                if (Objects.nonNull(messageTransfer.strInOut1()) && messageTransfer.strInOut1().length() > 0) {
//                    String[] inp = messageTransfer.strInOut1().split(";");
//
//                    System.out.println("inp <- ");
//                    Arrays.stream(inp).toList().forEach(System.out::println);
//
//                    int count =0;
//                    for (int i = 0; i < inp.length; i++) {
//                        try {
//                            int i1 = Integer.valueOf(inp[i].trim());
//                            if (i1 > 0 && i1 <= offerBooks.size()) {
//                                System.out.println("Kniha - " + inp[i] + " -> " + i1 + " " + offerBooks.get(i1).getTitle());
//                                offerBooks.get(i1).setLocked(Lock.UNLOCK);
//                            }
//                            count++;
//                        } catch (NumberFormatException e) {
//                            System.out.println("Chybný převod vstupního zadání 3.");
//                        }
//                    }
//                    menu.add("Koupě: " + count + " knih.");
//                    System.out.println(ServerHandler.threadName + " - " + myId + messageTransfer.toString());
//                    break;
//                }
//            }
//
//
//            return MessageTransfer.builder()
//                    .task(Const.MESSAGES_PRINT_TEXT)
//                    .replyTask(Const.DRIVER_RETURN_TO_ROOT)
//                    .menu(menu)
//                    .loop(false)
//                    .build();
//
//
//
//        }
//        //return MessageTransfer.builder().task(Const.EMPTY).build();
//    }
//
//}
