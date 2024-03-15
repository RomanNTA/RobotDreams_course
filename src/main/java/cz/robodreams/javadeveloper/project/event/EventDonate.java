//package cz.robodreams.javadeveloper.project.event;
//
//import cz.robodreams.javadeveloper.project.common.Util;
//
//import java.util.List;
//
//public class EventDonate extends AEvent {
//
//    private int donate;
//
//    public EventDonate(int donate) {
//        super(IEventType.DONATE, 0, 0);
//        this.donate = donate;
//    }
//
//    @Override
//    public Boolean run() {
//        print.add(String.format("Knihovna získala peněžitý dar ve výši " + Util.colRed("%d")  +" Kč",donate));
//        printEvent();
//        EventAccount.getInstance().setNewCash(donate).setMessage("Peněžitý dar.").run();
//        return true;
//    }
//
//    @Override
//    public Boolean isClose() {
//        return true;
//    }
//}
