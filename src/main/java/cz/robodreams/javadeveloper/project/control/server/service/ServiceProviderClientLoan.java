package cz.robodreams.javadeveloper.project.control.server.service;

import cz.robodreams.javadeveloper.project.article.articlebooks.interfaces.Book;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.server.ServerHandler;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

import static cz.robodreams.javadeveloper.project.control.common.Const.isNotNull;
import static cz.robodreams.javadeveloper.project.control.common.Const.testIndex;

public class ServiceProviderClientLoan extends ServiceProviderImpl implements ServiceProvider {

    private static Map<Integer, Book> freeBooks = new HashMap<>();

    public ServiceProviderClientLoan() {
        myId = Const.CLIENT_SEND_DLG_LOAN_BOOKS;
    }

    @Override
    public MessageTransfer sendOffer() {

        freeBooks = article.showFreeBooks(ShowSubjectItems.LONG_FORMAT);
        AtomicInteger counter = new AtomicInteger(1);

        menu.addAll(freeBooks.entrySet().stream().map(x -> ((Book) x.getValue()).getResultShow(ShowSubjectItems.INFO)).map(x -> (List<String>) x).peek(line -> line.add(Util.getLine())).peek(row -> row.add("  Kniha " + counter.getAndIncrement() + "\n")).flatMap(Collection::stream).toList());
        menu.add("Výběr knihy. Povolené zadání je:");
        menu.add(" - návrat bez výběru tj. číslo '0'");
        menu.add(" - číslo knihy např. '1'");
        menu.add(" - rozsah např.  knihy v pořadí '3-6'");
        menu.add(" - vše tj. písmeno 'A'");

//        System.out.println(ServerHandler.threadName + "CLIENT_SEND_DLG_LOAN_BOOKS " + freeBooks.size());
//        System.out.println(ServerHandler.threadName + "CLIENT_SEND_DLG_LOAN_BOOKS " + menu.size());
//
        return MessageTransfer.builder()
                .task(Const.MESSAGES_SEND_MENU_CHOICE_BOOKS)
                .strInOut1(Const.CLIENT_SEND_DLG_LOAN_BOOKS_label)
                .strInOut2(Const.CLIENT_SEND_DLG_LOAN_BOOKS_text)
                .replyTask(myId)
                .intResult(freeBooks.size())
                .menu(menu)
                .loop(false)
                .build();
    }



    @Override
    public MessageTransfer processAnswer() {

        BiFunction<Boolean, String, String> approvalStatus = (bool, tit) -> (
                (bool) ? Util.colGreen("Potvrzená") + " půjčka knihy " + tit :
                         Util.colRed("Zamítnutá") + " půjčka knihy " + tit);

        menu.clear();
        while (true) {

            System.out.println(ServerHandler.threadName + " - " + myId + " Příjem " + messageTransfer.toString());
            if (isNotNull.test(messageTransfer.intResult()) && messageTransfer.intResult() == 0) {
                //System.out.println("Návrat bez půjčky ");
                return MessageTransfer.builder().task(Const.CLIENT_RETURN_TO_ROOT).loop(true).build();
            }

            // jednu knihu
            if (isNotNull.test(messageTransfer.intResult()) && messageTransfer.intResult() > 0) {
                if (!testIndex.test(messageTransfer.intResult(), freeBooks.size() + 1)) {
                    throw new RuntimeException("Výměr mimo povolený rozsah");
                }
                //System.out.println("Kniha - " + freeBooks.get(messageTransfer.intResult()).getTitle());
                menu.add(approvalStatus.apply(lending.newLoan(serverHandler.getClient(), freeBooks.get(messageTransfer.intResult())),
                        freeBooks.get(messageTransfer.intResult()).getTitle()));
                break;
            }

            // všechny knihy
            if (isNotNull.test(messageTransfer.strInOut1()) && messageTransfer.strInOut1().equalsIgnoreCase("A")) {
                System.out.println("Půjčka všech knih ");
                freeBooks.values().forEach(x -> {
                    menu.add(approvalStatus.apply(lending.newLoan(serverHandler.getClient(), x), x.getTitle()));
                });
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
                        if (i1 > 0 && i1 <= freeBooks.size()) {
                            //System.out.println("Kniha - " + inp[i] + " -> " + i1 + " " + freeBooks.get(i1).getTitle());
                            menu.add(approvalStatus.apply(lending.newLoan(serverHandler.getClient(), freeBooks.get(i1)), freeBooks.get(i1).getTitle()));
                        }
                        count++;
                    } catch (NumberFormatException e) {
                        System.out.println("Chybný převod vstupního zadání 3.");
                    }
                }
                menu.add("Celkem půjčky: " + count + " knih.");
                System.out.println(ServerHandler.threadName + " - " + myId + messageTransfer.toString());
                break;
            }
        }

        return MessageTransfer.builder().task(Const.MESSAGES_PRINT_TEXT).replyTask(Const.CLIENT_RETURN_TO_ROOT).menu(menu).loop(false).build();
    }


//
//    @Override
//    public MessageTransfer run(MessageTransfer messageTransfer, ServerHandler serverHandler) {
//
//        myId = Const.CLIENT_SEND_DLG_LOAN_BOOKS;
//        System.out.println(ServerHandler.threadName + "CLIENT_SEND_DLG_LOAN_BOOKS - vstup");
//
//        if (Const.doesThisTaskEquals.test(messageTransfer, myId) && !Const.isStringFullAndNotNull.test(messageTransfer.replyTask())) {
//
//
//
//            freeBooks = article.showFreeBooks(ShowSubjectItems.LONG_FORMAT);
//
//
//            AtomicInteger counter = new AtomicInteger(1);
//
//            menu.addAll(freeBooks.entrySet().stream().map(x -> ((Book) x.getValue()).getResultShow(ShowSubjectItems.INFO)).map(x -> (List<String>) x).peek(line -> line.add(Util.getLine())).peek(row -> row.add("  Kniha " + counter.getAndIncrement() + "\n")).flatMap(Collection::stream).toList());
//
//            menu.add("Výběr knihy. Povolené zadání je:");
//            menu.add(" - návrat bez výběru tj. číslo '0'");
//            menu.add(" - číslo knihy např. '1'");
//            menu.add(" - rozsah např.  knihy v pořadí '3-6'");
//            menu.add(" - vše tj. písmeno 'A'");
//
//            System.out.println(ServerHandler.threadName + "CLIENT_SEND_DLG_LOAN_BOOKS " + freeBooks.size());
//            System.out.println(ServerHandler.threadName + "CLIENT_SEND_DLG_LOAN_BOOKS " + menu.size());
//
//            return MessageTransfer.builder().task(Const.MESSAGES_SEND_MENU_CHOICE_BOOKS).strInOut1(Const.CLIENT_SEND_DLG_LOAN_BOOKS_label).strInOut2(Const.CLIENT_SEND_DLG_LOAN_BOOKS_text).replyTask(myId).intResult(freeBooks.size()).menu(menu).loop(false).build();
//
//        } else {
//
//            BiFunction<Boolean, String, String> approvalStatus = (bool, tit) -> (
//                    (bool) ? Util.colGreen("Potvrzená") + " půjčka knihy " + tit :
//                            Util.colRed("Zamítnutá") + " půjčka knihy " + tit);
//
//            menu.clear();
//            while (true) {
//
//                System.out.println(ServerHandler.threadName + " - " + myId + " Příjem " + messageTransfer.toString());
//                if (isNotNull.test(messageTransfer.intResult()) && messageTransfer.intResult() == 0) {
//                    //System.out.println("Návrat bez půjčky ");
//                    return MessageTransfer.builder().task(Const.CLIENT_RETURN_TO_ROOT).loop(true).build();
//                }
//
//                // jednu knihu
//                if (isNotNull.test(messageTransfer.intResult()) && messageTransfer.intResult() > 0) {
//                    if (!testIndex.test(messageTransfer.intResult(), freeBooks.size() + 1)) {
//                        throw new RuntimeException("Výměr mimo povolený rozsah");
//                    }
//                    //System.out.println("Kniha - " + freeBooks.get(messageTransfer.intResult()).getTitle());
//                    menu.add(approvalStatus.apply(lending.newLoan(serverHandler.getClient(), freeBooks.get(messageTransfer.intResult())),
//                            freeBooks.get(messageTransfer.intResult()).getTitle()));
//                    break;
//                }
//
//                // všechny knihy
//                if (isNotNull.test(messageTransfer.strInOut1()) && messageTransfer.strInOut1().equalsIgnoreCase("A")) {
//                    System.out.println("Půjčka všech knih ");
//                    freeBooks.values().forEach(x -> {
//                        menu.add(approvalStatus.apply(lending.newLoan(serverHandler.getClient(), x), x.getTitle()));
//                    });
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
//                    int count = 0;
//                    for (int i = 0; i < inp.length; i++) {
//                        try {
//                            int i1 = Integer.valueOf(inp[i].trim());
//                            if (i1 > 0 && i1 <= freeBooks.size()) {
//                                //System.out.println("Kniha - " + inp[i] + " -> " + i1 + " " + freeBooks.get(i1).getTitle());
//                                menu.add(approvalStatus.apply(lending.newLoan(serverHandler.getClient(), freeBooks.get(i1)), freeBooks.get(i1).getTitle()));
//                            }
//                            count++;
//                        } catch (NumberFormatException e) {
//                            System.out.println("Chybný převod vstupního zadání 3.");
//                        }
//                    }
//                    menu.add("Celkem půjčky: " + count + " knih.");
//                    System.out.println(ServerHandler.threadName + " - " + myId + messageTransfer.toString());
//                    break;
//                }
//            }
//
//            return MessageTransfer.builder().task(Const.MESSAGES_PRINT_TEXT).replyTask(Const.CLIENT_RETURN_TO_ROOT).menu(menu).loop(false).build();
//        }
//    }

}
