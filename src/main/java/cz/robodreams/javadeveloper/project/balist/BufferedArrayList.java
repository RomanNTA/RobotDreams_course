package cz.robodreams.javadeveloper.project.balist;

import java.lang.reflect.Array;

public class BufferedArrayList<E> extends ArrayCalculator implements IBufferedArrayList<E> {

    private final Class<E[]> myType;

    /**
     * This will represent stored data
     */
    private E arr[];
    private E arrTmp[];

    private int allocCapacity;

    /***
     *  Suspend validation while appending to the end of an array.
     */
    private Boolean suspendVerify = false;

    public BufferedArrayList(Class<E[]> type) {
        super();
        this.myType = type;
        arr = createArray(capacity);
        arrTmp = null;
        size = 0;
    }

    public BufferedArrayList(Class<E[]> type, E... objArr) {
        this(type);
        if (objArr == null) {
            return;
        }
        resizeCapacity(objArr.length);
        System.arraycopy(objArr, 0, arr, 0, objArr.length);
        size = objArr.length;
    }

    private void changeTempAndArr() {
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
    public Boolean isEmpty() {
        return size == 0;
    }


    @Override
    public Boolean contains(E object) {

        for (int i = 0; i < size; i++) {
            if (((E) object) == ((E) arr[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void reCopyWithNewSize(int newSize) {
        arrTmp = createArray(newSize);
        System.arraycopy(arr, 0, arrTmp, 0, Math.min(arr.length, arrTmp.length));
        arr = arrTmp;
        arrTmp = null;
    }

    @Override
    public Boolean remove(E value) {
        int i = this.indexOf(value);
        if (i == -1) {
            return false;
        }
        return null; // removeFromTo(i, i);
    }

    /**
     * nerespektuje velikost a pokud je to mimo, nafoukne pole
     */
    @Override
    public void insert(E value, int index) {
        try {
            if (index < capacity) {
                arr[index] = value;
                size = size > index + 1 ? size : ++index;
            } else {
                insertFromTo(index, 1, value);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error 'insert' ... index= " + index + " size= " + size + " capacity= " + capacity);
        }
    }

    @Override
    public Boolean find(E value){
        for (int i = 0; i < size; i++) {
            if ( value.equals((E) arr[i])) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Boolean containsAll(E[] collection) {
        for (E o : collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean addAll(E... collection) {

        resizeCapacity(size + collection.length);
        System.arraycopy(collection, 0, arr, size, collection.length);
        size = size + collection.length;
        return true;
    }

    @Override
    public void add(E value) {
        resizeCapacity(size + 1);
        arr[size++] = value;
    }

    @Override
    public void clear() {
        super.clear();
        arr = createArray(capacity);
        arrTmp = null;
    }

    @Override
    public E get(int i) {
        verifyIndex(i);
        return (E) arr[i];
    }

    @Override
    public void set(int i, E value) {
        verifyIndex(i);
        arr[i] = (E) value;
    }


    @Override
    public int indexOf(E o) {

        for (int i = 0; i < size; i++) {
            if (arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Boolean remove(int index) {
        verifyIndex(index);
        return removeFromTo(index, index);
    }


    @Override
    public IBufferedArrayList<E> subList(int indexFrom, int indexTo) {

        verifyIndex(indexFrom, indexTo);
        int len = indexTo - indexFrom;
        E[] newArr = createArray(len);
        System.arraycopy(arr, indexFrom, newArr, 0, len);
        BufferedArrayList<E> my = new BufferedArrayList<E>(myType, newArr);
        return my;
    }

    /**
     * Procedura vynechá prvek/y na pozici "indexDelete"
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
            System.arraycopy(createArray(freeSize), 0, arr, size, freeSize);
        }
        resizeCapacity(size);
        return true;
    }

    private E[] createArray(int newSize) {
        return myType.cast(Array.newInstance(myType.getComponentType(), newSize));
    }

    private void verifyIndex(int i) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException("Error: verifyIndex: Out ouf bound exception");
        }
    }

    private void verifyIndex(int indexFrom, int indexTo) {
        if (indexFrom < 0 || indexTo >= size) {
            throw new ArrayIndexOutOfBoundsException("Error: verifyIndex: Out ouf bound exception");
        }
        if (indexFrom > indexTo) {
            throw new ArrayIndexOutOfBoundsException("Error: verifyIndex: Out ouf bound exception. (from > to)");
        }
    }

    private void copyToTmp(int arrTmpFromPosition, int extArrayPosition, int extArrayLength, E... arrayObjects) {
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
    public void insertFromTo(int indexOfInsert, int length, E... objArr) {

        if (objArr == null) {
            throw new NullPointerException("Error: insertFromTo: Null pointer exception.");
        }
        if (objArr.length < length) {
            throw new IndexOutOfBoundsException("Error: insertFromTo: The length of the field is less than the required length.");
        }
        if (length == 0) {
            return;
        }
        int newSize;
        if (indexOfInsert >= size) {
            newSize = Math.max(size, indexOfInsert + length);
            resizeCapacity(newSize);
            arrTmp = createArray(capacity);
            copyToTmp(0, 0, size, arr);
            copyToTmp(indexOfInsert, 0, length, objArr);
        } else {
            newSize = length + size;
            resizeCapacity(newSize);
            arrTmp = createArray(capacity);
            copyToTmp(0, 0, indexOfInsert, arr);
            copyToTmp(indexOfInsert, 0, length, objArr);
            copyToTmp(indexOfInsert + length, indexOfInsert, size - indexOfInsert, arr);
        }
        changeTempAndArr();
        size = newSize;
    }


}