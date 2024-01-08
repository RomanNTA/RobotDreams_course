package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {


    private static void naplnitPole(MyList pole, int pocet) {
        for (int i = 0; i < pocet; i++) {
            pole.add(i);
        }
    }

    private static void naplnitPole(MyArrayPetr pole, int pocet) {
        for (int i = 0; i < pocet; i++) {
            pole.add(i);
        }
    }

    private static void naplnitPole(List pole, int pocet) {
        for (int i = 0; i < pocet; i++) {
            pole.add(i);
        }
    }

    private static void naplnitPoleArrayList(List pole, int pocet) {
        for (int i = 0; i < pocet; i++) {
            Integer j = i;
            pole.add(j);
        }
    }
    private static void naplnitPoleArrayList(MyArrayPetr pole, int pocet) {
        for (int i = 0; i < pocet; i++) {
            Integer j = i;
            pole.add(j);
        }
    }

    private static void naplnitPoleArrayList(MyArrayList pole, int pocet) {
        for (int i = 0; i < pocet; i++) {
            Integer j = i;
            pole.add(j);
        }
    }

    public static void main(String[] args) {

//        testAdd();
//        testRemoveAccordingObject();
//        testConstructorWithArray();
//
//        testAddAll();
//        testContainsAll();
//        testIndexOf();
       testRemoveAccordingValue();
//        testSubList();
    }


    private static void testRemoveAccordingObject() {

        System.out.println("testRemoveAccordingObject----------------------------------------------------------------");

        MyList pole1 = new MyArrayList();
        naplnitPole(pole1, 15);

        List pole2 = new ArrayList<>();
        naplnitPoleArrayList(pole2, 15);

        MyArrayPetr pole3 = new MyArrayPetr();
        naplnitPoleArrayList(pole3, 15);


        // Zkouška remove podle objektu
        int i = pole1.size();
        while (pole1.size() > 0) {
            i--;
            pole1.remove((Object) i);
            pole2.remove((Object) i);
            pole3.remove((Object) i);

            System.out.println(pole1.toString() + " - " + i + " - " + pole1.isEmpty());
            System.out.println(pole2.toString() + " - " + i + " - " + pole2.isEmpty());
            System.out.println(pole3.toString() + " - " + i + " - " + pole3.isEmpty());
        }

        i = -1;
        while (pole1.size() > 0) {
            i++;
            pole1.remove((Object) i);
            pole2.remove((Object) i);
            pole3.remove((Object) i);

            System.out.println(pole1.toString() + " - " + i + " - " + pole1.isEmpty());
            System.out.println(pole2.toString() + " - " + i + " - " + pole2.isEmpty());
            System.out.println(pole3.toString() + " - " + i + " - " + pole3.isEmpty());
        }

    }

    private static void testRemoveAccordingValue() {
        System.out.println("testRemoveAccordingValue ----------------------------------------------------------------");

        MyList pole1 = new MyArrayListEx();
        naplnitPole(pole1, 10);

        List pole2 = new ArrayList<>();
        naplnitPoleArrayList(pole2, 10);

        MyArrayPetr pole3 = new MyArrayPetr();
        naplnitPoleArrayList(pole3, 10);

        // Zkouška remove podle hodnoty
//        int i = pole1.size();
//        while (pole1.size() > 0) {
//            i--;
//            pole1.remove((int) i);
//            System.out.println(pole1.toString() + " - " + i);
//        }
//
//        i = pole2.size();
//        while (pole2.size() > 0) {
//            i--;
//            pole2.remove((int) i);
//            System.out.println(pole2.toString() + " - " + i);
//        }
//
//        i = pole3.size();
//        while (pole3.size() > 0) {
//            i--;
//            pole3.remove((int) i);
//            System.out.println(pole3.toString() + " - " + i);
//        }


        // Zkouška remove podle hodnoty
        int i = pole1.size();
        while (pole1.size() > 0) {

            int j = getRandomId(0,pole1.size());

            System.out.println( " Roman ------------------------ " + j + " - " + pole1.get(j));

            System.out.println(pole1.toString() + " - " + j);
            pole1.remove((int) j);
            System.out.println(pole1.toString() + " - " + j);
        }

        while (pole2.size() > 0) {
            int j = getRandomId(0,pole2.size());

            System.out.println( " JAVA ------------------------ " + j + " - " + pole2.get(j));

            System.out.println(pole2.toString() + " - " + j);
            pole2.remove((int) j);
            System.out.println(pole2.toString() + " - " + j);
        }


        while (pole3.size() > 0) {
            int j = getRandomId(0,pole3.size());

            System.out.println( " Petr ------------------------ " + j + " - " + pole3.get(j));

            System.out.println(pole3.toString() + " - " + j);
            pole3.remove((int) j);
            System.out.println(pole3.toString() + " - " + j);
        }



    }

    public static Integer getRandomId(int min, int max ) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    private static void testContainsAll() {
        System.out.println("testContainsAll -------------------------------------------------------------------------");

        MyList pole1 = new MyArrayList();
        naplnitPole(pole1, 10);

        List pole2 = new ArrayList<>();
        naplnitPole(pole2, 10);

        MyArrayPetr pole3 = new MyArrayPetr();
        naplnitPole(pole3, 10);

        // Zkouška containsAll
//        System.out.println(pole.toString());

        List<Integer> p3 = Arrays.asList(2, 5, 7);
        List<Integer> p4 = Arrays.asList(2, 5, 7, 40);

        System.out.println(p3);
        System.out.println(p4);

        System.out.println(pole1.toString() + pole1.containsAll(p3));
        System.out.println(pole2.toString() + pole2.containsAll(p3));
        System.out.println(pole3.toString() + pole3.containsAll(p3));

        System.out.println(pole1.toString() + pole1.containsAll(p4));
        System.out.println(pole2.toString() + pole2.containsAll(p4));
        System.out.println(pole3.toString() + pole3.containsAll(p4));


    }


    private static void testIndexOf() {
        System.out.println("testIndexOf -----------------------------------------------------------------------------");

        MyList pole1 = new MyArrayList();
        naplnitPole(pole1, 10);

        List pole2 = new ArrayList<>();
        naplnitPoleArrayList(pole2, 10);

        MyArrayPetr pole3 = new MyArrayPetr();
        naplnitPole(pole3, 10);

        // Zkouška indexOf
        Integer int1 = 7;
        System.out.println(pole1.toString() + pole1.indexOf(int1));
        System.out.println(pole2.toString() + pole2.indexOf(int1));
        System.out.println(pole3.toString() + pole3.indexOf(int1));

    }

    private static void testSubList() {
        System.out.println("testSubList -----------------------------------------------------------------------------");

        MyArrayList pole1 = new MyArrayList();
        naplnitPoleArrayList(pole1, 10);

        List pole2 = new ArrayList<>();
        naplnitPoleArrayList(pole2, 10);

        MyArrayPetr pole3 = new MyArrayPetr();
        naplnitPoleArrayList(pole3, 10);

        // Zkouška objektu subList
        MyList pole11 = pole1.subList(2, 6);
        List pole22 = pole2.subList(2, 6);
        MyList pole33 = pole3.subList(2, 6);

        System.out.println(pole1.toString());
        System.out.println(pole2.toString());
        System.out.println(pole3.toString());

        System.out.println(pole11.toString());
        System.out.println(pole22.toString());
        System.out.println(pole33.toString());

    }


    private static void testConstructorWithArray() {
        // Zkouška konstruktoru s polem
        System.out.println("testConstructorWithArray ----------------------------------------------------------------");

        Integer[] pi1 = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        MyList pole1 = new MyArrayList(pi1);
        //List pole2 = new ArrayList(pi1);
        MyList pole3 = new MyArrayPetr(pi1);

        System.out.println(Arrays.toString(pi1));
        System.out.println(pole1.toString());
        //System.out.println(pole2.toString());
        System.out.println(pole3.toString());
    }

    private static void testAddAll() {
        // Zkouška konstruktoru s polem

        System.out.println("testAddAll ------------------------------------------------------------------------------");

        List<Integer> p1 = Arrays.asList(10, 20, 30, 40, 50);
        List<Integer> p2 = Arrays.asList(60, 70, 80, 90, 100, 110, 120);
        List<Integer> p3 = Arrays.asList(130, 140, 150, 160);
//-------

        MyList pole1 = new MyArrayList();
        pole1.addAll(p1);
        System.out.println(pole1.toString());
        pole1.addAll(p2);
        System.out.println(pole1.toString());
        pole1.addAll(p3);
        System.out.println(pole1.toString() + " - " + pole1.size());

        int i = pole1.size();
        while (pole1.size() > 0) {
            i--;
            pole1.remove(i);
            System.out.println(pole1.toString() + " - " + pole1.size());
        }
//-------

        List pole2 = new ArrayList();
        pole2.addAll(p1);
        System.out.println(pole2.toString());
        pole2.addAll(p2);
        System.out.println(pole2.toString());
        pole2.addAll(p3);
        System.out.println(pole2.toString() + " - " + pole2.size());

        i = pole2.size();
        while (pole2.size() > 0) {
            i--;
            pole2.remove(i);
            System.out.println(pole2.toString() + " - " + pole2.size());
        }
//-------
        MyArrayPetr pole3 = new MyArrayPetr();
        pole3.addAll(p1);
        System.out.println(pole3.toString());
        pole3.addAll(p2);
        System.out.println(pole3.toString());
        pole3.addAll(p3);
        System.out.println(pole3.toString() + " - " + pole3.size());

        i = pole3.size();
        while (pole3.size() > 0) {
            i--;
            pole3.remove(i);
            System.out.println(pole3.toString() + " - " + pole3.size());
        }

    }

    private static void testAdd() {

        System.out.println("testAdd ---------------------------------------------------------------------------------");

        MyArrayPetr pole1 = new MyArrayPetr();
        for (int i = 0; i < 10; i++) {
            pole1.add(i);
        }
        pole1.add(1);

        MyList pole3 = new MyArrayList();
        for (int i = 0; i < 10; i++) {
            pole3.add(i);
        }
        pole3.add(1);


        List pole2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pole2.add(i);
        }
        pole2.add(1);


        System.out.println(pole3.toString());
        System.out.println(pole2.toString());
        System.out.println(pole1.toString());

    }


}
