package cz.robodreams.javadeveloper.project.control.client;


import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.ServiceThread;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CommunicatorGetMenu extends ServiceThread<MessageTransfer> {

    public MessageTransfer go(MessageTransfer mt, Scanner console) {

        if (!Const.isNotNull.test(mt)) return null;
        if (!Const.isNotNull.test(console)) return null;

        this.task = () -> {

            try {
                synchronized (this) {
                    wait(300L);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            AtomicInteger position = new AtomicInteger(0);
            Util.line();

            System.out.println(String.format("| %s ", Util.colRed(mt.label())));
            System.out.println(String.format("| %s ", Util.colWhite(mt.remark())));
            mt.menu().forEach(x -> System.out.println(String.format("|" + Util.colRed("%3d") + " : %s", position.getAndIncrement(), x)));
            System.out.println(String.format("| %s ", Util.colWhite("Zadej číslo: ")));

            int result = 0;
            String stringLine = "";

            synchronized (console) {
                stringLine = console.nextLine();
            }

            try {
                result = Integer.valueOf(stringLine);
            } catch (NumberFormatException e) {
                System.out.println("Chybný převod STR to INT.");
            }
            System.out.println("přijato " + result);

            return MessageTransfer.builder()
                    .task(mt.replyTask())
                    .replyTask(Const.REPLY)
                    .output(result)
                    .build();
        };

        return isAllowedRun ? start() : null;
    }

}


