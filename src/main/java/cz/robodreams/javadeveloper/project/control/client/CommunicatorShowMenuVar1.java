package cz.robodreams.javadeveloper.project.control.client;


import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.control.common.Const;
import cz.robodreams.javadeveloper.project.control.common.MessageTransfer;
import cz.robodreams.javadeveloper.project.control.common.ServiceThread;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import static cz.robodreams.javadeveloper.project.control.common.Const.testIndex;

public class CommunicatorShowMenuVar1 extends ServiceThread<MessageTransfer> {

    public MessageTransfer go(MessageTransfer mt, Scanner console) {

        if (!Const.isNotNull.test(mt)) return null;
        if (!Const.isNotNull.test(console)) return null;

        this.task = () -> {

            //System.out.println(" mt.menu().size() " + mt.menu().size());

            //myWait(100L);

            AtomicInteger position = new AtomicInteger(0);
            Util.line();

            System.out.println(String.format("| %s ", Util.colRed(mt.strInOut1())));
            System.out.println(String.format("| %s ", Util.colWhite(mt.strInOut2())));
            mt.menu().forEach(x -> System.out.println(String.format("|" + Util.colRed("%3d") + " : %s", position.getAndIncrement(), x)));
            System.out.println(String.format("| %s ", Util.colWhite("Zadej číslo: ")));

            int result = 0;
            String stringLine = "";


            do {

                synchronized (console) {
                    stringLine = console.nextLine();
                }

                try {
                    result = Integer.valueOf(stringLine);

                    if (testIndex.test(result, mt.menu().size())) {
                        //System.out.println("přijato " + result);
                        break;
                    }
                    System.out.println("Výběr není v požadovaném rozsahu.");

                } catch (NumberFormatException e) {
                    System.out.println("Výběr není v požadovaném rozsahu.");

                }

            } while (true);


            return MessageTransfer.builder()
                    .task(mt.replyTask())
                    .replyTask(Const.REPLY)
                    .intResult(result)
                    .build();
        };

        return isAllowedRun ? start() : null;
    }

}


