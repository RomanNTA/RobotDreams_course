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

                    TestedRange testedRange = new TestedRange();
                    if (testedRange.test(stringLine,messageTransfer.intResult() + 1)){

                        return MessageTransfer.builder()
                                .task(messageTransfer.replyTask())
                                .replyTask(Const.REPLY)
                                .strInOut1(testedRange.getOutputString())
                                .intResult(-1)
                                .build();
                    } else {
                        System.out.println(testedRange.getErrorMessage());
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


