package cz.robodreams.javadeveloper.project.balist;

import java.util.Arrays;
import java.util.stream.Collectors;


public class BufferedArrayList implements IBufferedArrayList, InnerAccessToArrayList {

    /**
     * This will represent stored data
     */
    private Object[] arr;
    private Object[] arrTmp;

    private BufferedArrayListCalculator calc;

    private int size;
    private int position;

    public BufferedArrayList() {
        calc = new BufferedArrayListCalculator(this);
        arr = new Object[calc.capacity];
        arrTmp = null;
        size = 0;
        // allocCapacity = InnerAccessToArrayList.INIT_CAPACITY;
    }

    //    public BufferedArrayList(Collection<? extends Object> collection) {
    public BufferedArrayList(Object... extArr) {
        this();
        calc.realocate(extArr.length);
        arr = new Object[calc.capacity];
        System.arraycopy(extArr, 0, arr, 0, extArr.length);
    }


    /**
     * public pro kalkulátor, v interface není zahrnuta
     *
     * @param newSize
     */
    public void setSize(int newSize) {
        size = newSize;
    }

    @Override
    public int size() {
        return size;
    }

    public BufferedArrayListCalculator getCalc() {
        return calc;
    }

    public void setCalc(BufferedArrayListCalculator calc) {
        this.calc = calc;
    }


    @Override
    public int getAllocatedCapacity() {
        return arr.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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

    public void setPosition(int i) {
        position = i;
    }

    @Override
    public boolean add(Object value) {
        calc.resize(size + 1);
        position = size++;
        arr[position] = value;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int i = this.indexOf(o);
        if (i == -1) {
            return false;
        }
        return removeFromTo(i, i);
    }

    @Override
    public Boolean remove(int index) {
        verifyIndex(index);
        return removeFromTo(index, index);
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

    @Override
    public boolean containsAll(Object... objArr) {

        for (Object c : objArr) {
            if (!contains(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Object... objArr) {

        calc.resize(size + objArr.length);
        System.arraycopy(objArr, 0, arr, size, objArr.length);
        size = size + objArr.length;
        return true;
    }

    @Override
    public void clear() {

        arr = new Object[InnerAccessToArrayList.INIT_CAPACITY];
        arrTmp = null;
        size = 0;
    }

    @Override
    public Object get(int i) {

        verifyIndex(i);
        return arr[i];
    }

    /**
     * nerespektuje velikost a pokud je to mimo, nafoukne pole
     */
    @Override
    public void insert(Object o, int index) {
        if (index < calc.capacity) {
            arr[index] = o;
            size = size > index + 1 ? size : ++index;
        } else {
            insertFromTo(index, 1, o);
        }
    }

    /**
     * respektuje velikost a pokud je to mimo, přeteče
     */
    @Override
    public Object set(int i, Object value) {

        verifyIndex(i);
        arr[i] = value;
        return value;
    }


    @Override
    public IBufferedArrayList subList(int indexFrom, int indexTo) {

        verifyIndex(indexFrom, indexTo);
        int len = indexTo - indexFrom;
        Object[] newArr = new Object[len];
        System.arraycopy(arr, indexFrom, newArr, 0, len);
        BufferedArrayList my = new BufferedArrayList(Arrays.asList(newArr));
        return my;

    }


    /**
     * textové výstupy
     */
    @Override
    public String getStreamToString() {
        return Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(", "));
    }

    @Override
    public String toString() {

        String out = Arrays.toString(arr);
        out = getAllocatedCapacity() + "/" + size() + "{" + out.substring(1, out.length() - 1) + "}";
        return out;
    }


    /**
     * Procedura kopiruje pole z "arr" do "arrTmp" a vynechá prvek na pozici "indexDelete"
     */
    @Override
    public Boolean removeFromTo(int indexFrom, int indexTo) {

        verifyIndex(indexFrom, indexTo);
        int oldSize = size;
        size = size - (indexTo - indexFrom) - 1;
        int freeSize = oldSize - size;
        System.arraycopy(arr, indexTo + 1, arr, indexFrom, oldSize - indexTo - 1);
        if (freeSize == 1 && getAllocatedCapacity() >= size) {
            arr[size] = null;
        } else {
            Object[] t = new Object[freeSize];
            System.arraycopy(t, 0, arr, size, freeSize);
        }
        calc.resize(size);
        return true;
    }


    /**
     * kopírování z exteru do arrTmp
     *
     * @param fromExtPosition - od pozice (src)
     * @param toPosition      - pozicev arrTmp
     * @param length          - délka
     * @param extArr          - odkaz na src
     */
    private void copyToTmp(int arrTmpFromPosition, int extArrayPosition, int extArrayLength, Object... arrayObjects) {
        try {
            System.arraycopy(arrayObjects, extArrayPosition, arrTmp, arrTmpFromPosition, extArrayLength);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Error: Exception caught in catch block");
        }
    }

    /**
     * Procedura vloží prvky do pole podle zadaných indexů.
     * Pokud přeteče, tak se nafoukne a mezery budou null.
     */
    @Override
    public void insertFromTo(int indexOfInsert, int length, Object... objArr) {

        if (objArr == null) {
            throw new NullPointerException("Null pointer exception.");
        }
        // verifyIndex(indexFrom, indexTo);
        if (indexOfInsert >= size) {
            int newSize = Math.max(size, indexOfInsert + length);
            calc.resize(newSize);
            createTempArr();
            copyToTmp(0, 0, size, arr);
            copyToTmp(indexOfInsert, 0, length, objArr);
            changeTempAndArr();
            size = newSize;
        } else {
            int newSize = length + size;
            calc.resize(newSize);
            createTempArr();
            copyToTmp(0, 0, indexOfInsert, arr);
            copyToTmp(indexOfInsert, 0, length, objArr);
            copyToTmp(indexOfInsert + length, indexOfInsert, size - indexOfInsert, arr);
            changeTempAndArr();
            size = newSize;
        }
    }


    /**
     * Volá kalkulátor při přetečení
     *
     * @param newSize
     */
    public void reCopyWithNewSize(int newSize) {
        arrTmp = new Object[newSize];
        System.arraycopy(arr, 0, arrTmp, 0, Math.min(arr.length, arrTmp.length));
        arr = arrTmp;
        arrTmp = null;
    }


    protected void createTemporaryArray() {
        arrTmp = new Object[calc.capacity];
    }

    protected void createTempArr() {
        createTemporaryArray();
        int length = Math.min(Math.min(arr.length, arrTmp.length), size);
        System.arraycopy(arr, 0, arrTmp, 0, length);
    }

    protected void verifyIndex(int i) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException("Out ouf bound exception");
        }
    }

    protected void verifyIndex(int indexFrom, int indexTo) {
        if (indexFrom < 0 || indexTo >= size) {
            throw new ArrayIndexOutOfBoundsException("Out ouf bound exception");
        }
        if (indexFrom > indexTo) {
            throw new ArrayIndexOutOfBoundsException("Out ouf bound exception. (from > to)");
        }

    }


    protected void changeTempAndArr() {
        arr = arrTmp;
        arrTmp = null;
    }


}