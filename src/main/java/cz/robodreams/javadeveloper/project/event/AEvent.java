//package cz.robodreams.javadeveloper.project.event;
//
//import cz.robodreams.javadeveloper.project.common.UtilConst;
//import cz.robodreams.javadeveloper.project.common.Util;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public abstract class AEvent implements IEvent, UtilConst {
//
//    // životní cyklus
//    private Integer lifeCycle;
//
//    // ukončen životní cyklus
//    private Boolean close;
//
//    // typ
//    private IEventType event;
//
//    // textový výstup
//    protected List<String> print = new ArrayList<>();
//
//
//    public AEvent(IEventType event, int lifeMin, int lifeMax) {
//        this.event = event;
//        this.lifeCycle = Util.getRandomId(lifeMin, lifeMax);
//    }
//
//    @Override
//    public IEventType getEvent() {
//        return null;
//    }
//
////    public void add(IEvent value) {
////        return;
////    }
//
//
//    @Override
//    public Boolean run() {
//        return false;
//    }
//
//
//    @Override
//    public void line() {
//        this.print.add("+" + "-".repeat(90));
//    }
//
//
//    @Override
//    public void printEvent() {
//        line();
//        this.print.forEach(System.out::println);
//    }
//
//    @Override
//    public Integer getLifeCycle() {
//        return lifeCycle;
//    }
//
//    @Override
//    public Boolean isClose() {
//        close = this.lifeCycle <= 0;
//        return close;
//    }
//
//}
