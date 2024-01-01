package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

// import cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist.MyArrayList.RealocateOperation;

// https://beginnersbook.com/2013/12/java-arraylist/
// Popis jak funguje ArrayList


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

public class MyArrayListEx implements MyList, InnerAccessToArrayList {

    /**
     * This will represent stored data
     */
    Integer[] arr;
    int size;


    private Integer[] arrTmp;

    private int position;

    private MyArrayListCalculator calc;


    public MyArrayListEx() {
        calc = new MyArrayListCalculator(this);
        arr = new Integer[calc.capacity];
        arrTmp = null;
        size = 0;
        // allocCapacity = InnerAccessToArrayList.INIT_CAPACITY;
    }

    public MyArrayListEx(Integer[] extArray) {

        this();

        calc.realocate(extArray.length);
        createTemporaryArray();
        copyFromExternalArray(0, 0, extArray.length, extArray);
        changeArr();
    }


    private void changeArr() {
        arr = null;
        arr = arrTmp;
        arrTmp = null;
    }


    @Override
    public int getAllocatedCapacity() {
        return arr.length;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {

        for (int i = 0; i < size; i++) {
            if (arr[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    public void setSize(int newSize) {
        size = newSize;
    }

    private void createTemporaryArray() {
        arrTmp = new Integer[calc.capacity];
    }

    private void createTemporaryArray(int newSize) {
        arrTmp = new Integer[newSize];
    }

    private void setTemporaryArray() {
        createTemporaryArray();
        int length = Math.min(Math.min(arr.length, arrTmp.length), size);
        System.arraycopy(arr, 0, arrTmp, 0, length);
        //return length;
    }

    private void insertTmp(Integer i) {
        arrTmp[position] = i;
        // position je řízený calc
    }

    private void insert(Integer i) {
        arr[position] = i;
        // position je řízený calc
    }

    public void setPosition(int i) {
        position = i;
    }

    @Override
    public boolean add(Integer integer) {

        if (calc.realocate(size + 1)) {
            setTemporaryArray();
            insertTmp(integer);
            changeArr();
        } else {
            insert(integer);
        }
        return true;
    }


    @Override
    public boolean remove(Object o) {

        int i = this.indexOf(o);
        if (i == -1) {
            return false;
        }

        // připraví buffer
        calc.realocate(size - 1);
        // překopíruje
        removeIndex(i);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {

        for (Object c : collection.toArray()) {
            if (!contains(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {

        Integer[] intCollection = collection.toArray(new Integer[collection.size()]);

        calc.realocate(size + intCollection.length);
        setTemporaryArray();
        copyFromExternalArray(0, position, intCollection.length, intCollection );
        changeArr();

        return true;
    }

    @Override
    public void clear() {
        arr = new Integer[InnerAccessToArrayList.INIT_CAPACITY];
        arrTmp = null;
        size = 0;
    }

    @Override
    public Integer get(int i) {

        if (i < size && i >= 0) {
            return arr[i];
        }
        return null;
    }

    @Override
    public Integer set(int i, Integer integer) {

        if (i < size && i >= 0) {
            return arr[i] = integer;
        }
        return integer;
    }

    @Override
    public Integer remove(int index) {

        if (index >= size || index < 0) {
            return null;
        }
        Integer result = arr[index];
        calc.realocate(size - 1);
        removeIndex(index);
        return result;
    }


    @Override
    public int indexOf(Object o) {

        for (int i = 0; i < size; i++) {
            if (arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public MyList subList(int i, int i1) {

        if (i1 >= size || i < 0) {
            return null;
        }

        int len = i1 - i;
        Integer[] newArr = new Integer[len]; // little help:  in newArr prepare data for sublist.
        System.arraycopy(arr, i, newArr, 0, len);

        return new MyArrayListEx(newArr);
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    /**
     * kopírování první části do arrTmp
     * @param lastPosition ... kopiruje od počátku do pozice
     */
    private void copyFromStartToPosition(int lastPosition) {
        if (lastPosition > 0 && lastPosition <= size()) {
            System.arraycopy(arr, 0, arrTmp, 0, lastPosition);
        }
    }

    /**
     * kopírování druhé části do arrTmp
     * @param firstPosition ... kopiruje od pozice do konce
     */
    private void copyFromPositionToEnd(int firstPosition) {
        int len = size - firstPosition + 1;
        if (len > 0) {
            System.arraycopy(arr, firstPosition, arrTmp, firstPosition - 1, len);
        }
    }

    /**
     * kopírování z exteru do arrTmp
     * @param fromExtPosition - od pozice (src)
     * @param toPosition - pozicev arrTmp
     * @param length - délka
     * @param extArr - odkaz na src
     */
    private void copyFromExternalArray(int fromExtPosition, int toPosition, int length, Integer ... extArr) {
        if (length > 0 && extArr != null) {
            System.arraycopy(extArr, fromExtPosition, arrTmp, toPosition, length);
        }
    }


    /**
     * Procedura kopiruje pole z "arr" do "arrTmp" a vynechá prvek na pozici "indexDelete"
     */
    private void removeIndex(int indexDelete) {

        //System.out.println("Vymazat index " + indexDelete);
        createTemporaryArray();
        copyFromStartToPosition(indexDelete);
        copyFromPositionToEnd(indexDelete + 1);
        changeArr();
    }


}