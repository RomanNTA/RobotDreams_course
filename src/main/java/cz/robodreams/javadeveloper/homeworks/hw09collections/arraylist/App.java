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

    private static void naplnitPoleArrayList(List pole, int pocet){
        for (int i = 0; i < pocet; i++) {
            Integer j = i;
            pole.add(j);
        }
    }

    public static void main(String[] args) {

        testAdd();
        testRemoveAccordingObject();
        testConstructorWithArray();

        testAddAll();
        testContainsAll();
        testIndexOf();
        testRemoveAccordingValue();
        testSubList();
    }


    private static void testRemoveAccordingObject() {

        System.out.println( "testRemoveAccordingObject----------------------------------------------------------------");

        MyList pole1 = new MyArrayList();
        naplnitPole(pole1, 15);

        List pole2 = new ArrayList<>();
        naplnitPoleArrayList(pole2,15);

        // Zkouška remove podle objektu
        int i = pole1.size();
        while (pole1.size() > 0) {
            i--;
            pole1.remove((Object) i);
            pole2.remove((Object) i);
            System.out.println(pole1.toString() + " - " + i + " - " + pole1.isEmpty());
            System.out.println(pole2.toString() + " - " + i + " - " + pole2.isEmpty());
        }

        i = -1;
        while (pole1.size() > 0) {
            i++;
            pole1.remove((Object) i);
            pole2.remove((Object) i);
            System.out.println(pole1.toString() + " - " + i + " - " + pole1.isEmpty());
            System.out.println(pole2.toString() + " - " + i + " - " + pole2.isEmpty());
        }

    }

    private static void testRemoveAccordingValue() {
        System.out.println( "testRemoveAccordingValue ----------------------------------------------------------------");

        MyList pole = new MyArrayList();
        naplnitPole(pole, 10);

        List ara = new ArrayList<>();
        naplnitPoleArrayList(ara,10);

        // Zkouška remove podle hodnoty
        int i = pole.size();
        while (pole.size() > 0) {
            i--;
            ara.remove((int) i);
            pole.remove((int) i);
            System.out.println(ara.toString() + " - " + i);
            System.out.println(pole.toString() + " - " + i);
        }
    }


    private static void testContainsAll(){
        System.out.println( "testContainsAll -------------------------------------------------------------------------");

        MyList pole = new MyArrayList();
        naplnitPole(pole, 10);

        // Zkouška containsAll
//        System.out.println(pole.toString());
        List<Integer> p3 = Arrays.asList(2,5,7);
        System.out.println(p3);
        System.out.println(pole.toString() + pole.containsAll(p3));

        List<Integer> p4 = Arrays.asList(2,5,7,40);
        System.out.println(p4);
        System.out.println(pole.toString() + pole.containsAll(p4));


    }



    private static void testIndexOf(){
        System.out.println( "testIndexOf -----------------------------------------------------------------------------");

        MyList pole = new MyArrayList();
        naplnitPole(pole, 10);

        List ara = new ArrayList<>();
        naplnitPoleArrayList(ara,10);

        // Zkouška indexOf
        Integer int1 = 7;
        System.out.println(pole.toString() + pole.indexOf(int1));
        System.out.println(ara.toString() + ara.indexOf(int1));

    }



    private static void testSubList(){
        System.out.println( "testSubList -----------------------------------------------------------------------------");

        MyList pole1 = new MyArrayList();
        naplnitPole(pole1, 10);

        List pole2 = new ArrayList<>();
        naplnitPoleArrayList(pole2,10);

        // Zkouška objektu subList
        MyList pole3 = pole1.subList(2,6);
        List pole4 = pole2.subList(2,6);

        System.out.println(pole1.toString());
        System.out.println(pole3.toString());

        System.out.println(pole2.toString());
        System.out.println(pole4.toString());

    }


    private static void testConstructorWithArray(){
        // Zkouška konstruktoru s polem
        System.out.println( "testConstructorWithArray ----------------------------------------------------------------");

        Integer[] pi1 = {10,20,30,40,50,60,70,80,90};
        MyList pole = new MyArrayList(pi1);

        System.out.println(Arrays.toString(pi1));
        System.out.println(pole.toString());
    }

    private static void testAddAll(){
        // Zkouška konstruktoru s polem

        System.out.println( "testAddAll ------------------------------------------------------------------------------");

        List<Integer> p1 = Arrays.asList(10, 20, 30, 40, 50);
        List<Integer> p2 = Arrays.asList(60, 70, 80, 90, 100, 110, 120);

        MyList pole = new MyArrayList();
        pole.addAll(p1);
        System.out.println(pole.toString());
        pole.addAll(p2);
        System.out.println(pole.toString());
        pole.addAll(p2);

        System.out.println(pole.toString() + " - " + pole.size())  ;

        int i = pole.size();
        while (pole.size() > 0) {
            i--;
            pole.remove(i);
            System.out.println(pole.toString() + " - " + pole.size())  ;
        }

    }

    private static void testAdd(){

        System.out.println( "testAdd ---------------------------------------------------------------------------------");

        MyList pole1 = new MyArrayListEx();
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



        System.out.println(pole1.toString());
        System.out.println(pole3.toString());
        System.out.println(pole2.toString());

    }




}
