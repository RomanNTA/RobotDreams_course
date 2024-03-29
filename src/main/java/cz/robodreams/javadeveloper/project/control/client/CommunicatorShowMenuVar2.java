package cz.robodreams.javadeveloper.project.control.client;


import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.ServiceThread;

import java.util.Scanner;

import static cz.robodreams.javadeveloper.project.control.common.Const.isNotNull;
import static cz.robodreams.javadeveloper.project.control.common.Const.testIndex;

public class CommunicatorShowMenuVar2 extends ServiceThread<MessageTransfer> {

    public MessageTransfer go(MessageTransfer messageTransfer, Scanner console) {

        if (!isNotNull.test(messageTransfer)) return null;
        if (!isNotNull.test(console)) return null;

        this.task = () -> {

            if (!isNotNull.test(messageTransfer)) {
                return MessageTransfer.builder()
                        .task(Const.CLIENT_RETURN_TO_ROOT)
                        .build();
            }

            if (!isNotNull.test(messageTransfer.menu())) {
                return MessageTransfer.builder()
                        .task(Const.CLIENT_RETURN_TO_ROOT)
                        .build();
            }
            messageTransfer.menu().forEach(System.out::println);

            String result = "";
            String stringLine = "";

            do {

                synchronized (console) {
                    stringLine = console.nextLine();
                }

                if (stringLine.equalsIgnoreCase("A")) {

                    return MessageTransfer.builder()
                            .task(messageTransfer.replyTask())
                            .strInOut1("A")
                            .intResult(-1)
                            .replyTask(Const.REPLY)
                            .build();
                }

                if (stringLine.contains("-")) {

                    result = "";
                    String[] out = stringLine.split("-");
                    if (out.length == 2) {

                        try {
                            int i1 = Integer.valueOf(out[0]);
                            int i2 = Integer.valueOf(out[1]);

                            if (!testIndex.test(i1, messageTransfer.intResult() + 1) || !testIndex.test(i2, messageTransfer.intResult() + 1)) {
                                System.out.println("Výběr není v požadovaném rozsahu");
                                continue;
                            }

                            for (int i = Math.min(i1, i2); i <= Math.max(i1, i2); i++) {
                                result += i + ";";
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Chybný převod vstupního zadání");
                            continue;
                        }

                        return MessageTransfer.builder()
                                .task(messageTransfer.replyTask())
                                .replyTask(Const.REPLY)
                                .strInOut1(result)
                                .intResult(-1)
                                .build();

                    } else {
                        System.out.println("Chybný převod vstupního zadání");
                        continue;
                    }
                }

                int i;
                try {
                    i = Integer.valueOf(stringLine);
                } catch (NumberFormatException e) {
                    System.out.println("Chybný převod vstupního zadání.");
                    continue;
                }

                return MessageTransfer.builder()
                        .task(messageTransfer.replyTask())
                        .replyTask(Const.REPLY)
                        .intResult(i)
                        .build();

            } while (true);
        };

        return isAllowedRun ? start() : null;
    }

}


