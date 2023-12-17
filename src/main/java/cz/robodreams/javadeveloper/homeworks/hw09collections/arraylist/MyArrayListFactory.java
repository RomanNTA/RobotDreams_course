package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

public class MyArrayListFactory {

    private MyArrayListFactory() {
    }

    public static MyList createMyArrayListInstance() {
        return new MyArrayList();
    }

}
