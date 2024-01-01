package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;



public class MyArrayListExFactory {

    private MyArrayListExFactory() {
    }

    public static MyList createMyArrayListExInstance() {
        return new MyArrayListEx();
    }

}
