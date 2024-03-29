//package cz.robodreams.javadeveloper.project.control.common;
//
//import cz.robodreams.javadeveloper.project.common.Util;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class PrintOutputImpl implements Serializable {
//
//    private String strInOut1;
//    private String text;
//    private List<String> choiceList = new ArrayList<>();
//
//    public PrintOutputImpl(String strInOut1, String text) {
//        this.strInOut1 = strInOut1;
//        this.text = text;
//    }
//
////    public PrintOutputImpl add(String string) {
////        choiceList.add(string);
////        return this;
////    }
//
//
//    public void show() {
//        AtomicInteger position = new AtomicInteger(0);
//        Util.line();
//        System.out.println(String.format("| %s ", Util.colRed(strInOut1)));
//        System.out.println(String.format("| %s ", Util.colWhite(text)));
//        choiceList.stream()
//                .forEach(x -> System.out.println(String.format("|" + Util.colRed("%3d") + " : %s",position.getAndIncrement(),x)));
//
//    }
//
//    public String get(int index) {
//        return (Const.testIndex.test(index, choiceList.size())) ? choiceList.get(index) : "";
//    }
//
//
//}
