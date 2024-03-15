//package cz.robodreams.javadeveloper.project.event;
//
//import cz.robodreams.javadeveloper.project.common.Util;
//import lombok.Getter;
//
//public class EventAccount extends AEvent {
//
//    @Getter
//    private static Integer cash;
//
//    static {
//        cash = 0;
//    }
//
//    private static EventAccount single_instance;
//
//    static {
//        single_instance = null;
//    }
//
//    @Getter
//    private Integer newCash;
//    @Getter
//    private String message = "";
//
//
//    public EventAccount() {
//        super(IEventType.ACCOUNT, 0, 0);
//    }
//
//    public static EventAccount getInstance()
//    {
//        if (single_instance == null)
//            single_instance = new EventAccount();
//        return single_instance;
//    }
//
//
//
//    @Override
//    public Boolean run() {
//        super.run();
//        print.clear();
//
//        if (newCash == 0) {
//            print.add(String.format("Na účtu je zůstatek " + Util.colRed("%d") + " Kč", this.cash));
//        }
//
//        if (newCash > 0) {
//            this.cash += newCash;
//            print.add(String.format("Příchozí platba " + Util.colRed("%d") + " Kč '" + Util.colGreen("%s") + "'. Konečný stav na účtu je " + Util.colRed("%d") + " Kč", newCash, message, this.cash));
//        }
//
//        if (newCash < 0) {
//
//            if (-newCash > this.cash) {
//                this.cash -= newCash;
//                print.add(String.format("Odchozí platba " + Util.colRed("%d") + " Kč za '" + Util.colGreen("%s") + "' nebyla provedena.", newCash, message));
//                print.add(String.format(Util.colRed("Nedostatek") + ". Konečný stav na účtu je " + Util.colRed("%d") + " Kč", this.cash));
//            } else {
//                this.cash += newCash;
//                print.add(String.format("Odchozí platba " + Util.colRed("%d") + " Kč '" + Util.colPurple("%s") + "' Konečný stav na účtu je " + Util.colRed("%d") + " Kč", newCash, message, this.cash));
//            }
//        }
//        printEvent();
//        return true;
//    }
//
//
//    @Override
//    public Boolean isClose() {
//        return false;
//    }
//
//    public EventAccount setNewCash(Integer newCash) {
//        this.newCash = newCash;
//        return this;
//    }
//
//    public EventAccount setMessage(String message) {
//        this.message = message;
//        return this;
//    }
//
//}
