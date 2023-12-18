package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

import java.util.Collection;

public class MyArrayList implements MyList, InnerAccessToArrayList {

    /**
     * This will represent stored data
     */
    Integer[] arr;
    int size;

    public MyArrayList() {
    }

    public MyArrayList(Integer[] arr) {
    }

    @Override
    public int getAllocatedCapacity() {
        return -1;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean add(Integer integer) {
        return true;
    }



    @Override
    public boolean remove(Object o) {
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {
        return false;
    }


    @Override
    public void clear() {
    }

    @Override
    public Integer get(int i) {
        return 1;
    }

    @Override
    public Integer set(int i, Integer integer) {
        return 1;
    }

    @Override
    public Integer remove(int i) {
        return 1;
    }


    @Override
    public int indexOf(Object o) {

        return -1;
    }

    @Override
    public MyList subList(int i, int i1) {
        Integer[] newArr = new Integer[0]; // little help:  in newArr prepare data for sublist.
        return new MyArrayList(newArr);
    }

}
