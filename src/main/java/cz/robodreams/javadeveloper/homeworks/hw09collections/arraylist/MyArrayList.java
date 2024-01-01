package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

// https://beginnersbook.com/2013/12/java-arraylist/
// Popis jak funguje ArrayList


import java.util.Arrays;
import java.util.Collection;

public class MyArrayList implements MyList, InnerAccessToArrayList {

    /**
     * This will represent stored data
     */
    Integer[] arr;
    int size;

    /**
     * arrTmp ... druhé pomocné pole, ve kterém se připraví velikost, překopíruje a pak se vymění s hlavním.
     * Důvodem je, že JAVA neumí navýšit velikost pole a při každé operaci se musí vytvořit nové.
     */
    private Integer[] arrTmp;

    /**
     * allocCapacity ... celková alokovaná velikost pole
     */
    private int allocCapacity;

    /* 1,2  => 120% */
    private final Double iclf = (double) (INCREASE_CAPACITY_LOAD_FACTOR + 100) / 100;
    /* 0,25 => 25%  */
    private final Double dcdf = (double) DECREASE_CAPACITY_DECISION_FACTOR / 100;
    /* 0,5  => 50%  */
    private final Double dcf = (double) DECREASE_CAPACITY_FACTOR / 100;


    private enum RealocateOperation {PLUS_CAPACITY, MINUS_CAPACITY}





    public MyArrayList() {
        arr = new Integer[InnerAccessToArrayList.INIT_CAPACITY];
        arrTmp = null;
        size = 0;
        allocCapacity = InnerAccessToArrayList.INIT_CAPACITY;
    }

    public MyArrayList(Integer[] arr) {

        this();

        size = arr.length;
        __reAllocate(RealocateOperation.PLUS_CAPACITY, true);
        System.arraycopy(arr, 0, arrTmp, 0, arr.length);
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
            if (arr[i] == o) {
                return true;
            }
        }
        return false;
    }


    /**
     * Procedura kopiruje pole z "arr" do "arrTmp" a vynechá prvek na pozici "indexDelete"
     */
    private void __reCopy(int indexDelete) {

        if (indexDelete == 0) {              /* první */
            if (size == 0) {
                arrTmp[1] = null;
                return;
            }
            System.arraycopy(arr, 1, arrTmp, 0, size);
        } else if (indexDelete == size) {   // poslední

            if (indexDelete == 1 && size == 1) {
                arrTmp[0] = arr[0];
            } else if (indexDelete == 2 && size == 2) {
                arrTmp[0] = arr[0];
                arrTmp[1] = arr[1];
            } else {
                System.arraycopy(arr, 0, arrTmp, 0, size);
            }
        } else {                       // mezi
            System.arraycopy(arr, 0, arrTmp, 0, indexDelete);
            System.arraycopy(arr, indexDelete + 1, arrTmp, indexDelete, size - indexDelete);
        }

    }


    private Integer[] __reAllocate(RealocateOperation operation, Boolean mustBeTmp) {

        /**
         * Aktualizuje allocCapacity podle potřeby.
         * V případě aktualizace vrací odkaz na "private arrTmp" nebo null (pokud se nic němění)
         * V případě mustBeTmp = true vrací vždy nové prazdné pole "private arrTmp"
         *
         */

//        System.out.println("Změna kapacity ----------------------------");
        //System.out.println(iclf + " - " + dcdf + " - " + dcf);

        if (operation == RealocateOperation.PLUS_CAPACITY) {
            // Přidání prvku(ů)

            if (size <= allocCapacity) {

//               return (mustBeTmp) ?  new Integer[allocCapacity] : null;
                if (mustBeTmp) {
                    arrTmp = new Integer[allocCapacity];
//                      System.out.println("Buffer - size / allocCapacity " + size + "/" + allocCapacity);
                    return arrTmp;
                } else {
                    return null;
                }

            } else {

                // Nafouknutí kapacity
                allocCapacity = (int) Math.ceil((double) size * iclf);

                arrTmp = new Integer[allocCapacity];
//                  System.out.println("Nafuk - size / allocCapacity " + size + "/" + allocCapacity);
                return arrTmp;
            }
        }

        if (operation == RealocateOperation.MINUS_CAPACITY) {

            int sizeMin = (int) Math.ceil((double) allocCapacity * dcdf);

            if (sizeMin < size) {  // je to mimo povolenou oblast - vyfukujeme 50% volné oblasti

                int alocDcf = size + (int) Math.ceil((double) (allocCapacity - size) * dcf);

                if (alocDcf < INIT_CAPACITY) {
                    allocCapacity = INIT_CAPACITY;
                } else {
                    allocCapacity = alocDcf;
                }

                arrTmp = new Integer[allocCapacity];
//                  System.out.println("Vyfuk - size / allocCapacity " + size + "/" + allocCapacity);
                return arrTmp;

            } else {  // je to v povolené oblasti

//                  System.out.println("OK - v oblasti - size / allocCapacity " + size + "/" + allocCapacity);
                if (mustBeTmp) {
                    if (size <= INIT_CAPACITY) {
                        allocCapacity = INIT_CAPACITY;
                    }
                    arrTmp = new Integer[allocCapacity];
//                      System.out.println("Buffer - size / allocCapacity " + size + "/" + allocCapacity);
                    return arrTmp;
                } else {
                    return null;
                }
            }
        }

        // vyvolat chybu !!!
        return null;
    }


    @Override
    public boolean add(Integer integer) {

        size++;
        __reAllocate(RealocateOperation.PLUS_CAPACITY, false);
        if (arrTmp != null) {
            System.arraycopy(arr, 0, arrTmp, 0, arr.length);
            arrTmp[size - 1] = integer;
            changeArr();
        } else {
            arr[size - 1] = integer;
        }
        return true;
    }


    @Override
    public boolean remove(Object o) {

        int i = this.indexOf(o);
        if (i == -1) {
            return false;
        }

        size--;
        __reAllocate(RealocateOperation.MINUS_CAPACITY, true);
        __reCopy(i);

        changeArr();
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

        int sizeOld = size;
        size = size + collection.size();

        __reAllocate(RealocateOperation.PLUS_CAPACITY, true);
        System.arraycopy(arr, 0, arrTmp, 0, sizeOld);
        System.arraycopy(collection.toArray(), 0, arrTmp, sizeOld, collection.size());

        changeArr();
        return true;
    }

    @Override
    public void clear() {
        arr = new Integer[InnerAccessToArrayList.INIT_CAPACITY];
        arrTmp = null;
        allocCapacity = InnerAccessToArrayList.INIT_CAPACITY;
        size = 0;
    }

    @Override
    public Integer get(int i) {

        if (i < size) {
            return arr[i];
        }
        return null;
    }

    @Override
    public Integer set(int i, Integer integer) {

        if (i < size) {
            return arr[i] = integer;
        }
        return integer;
    }

    @Override
    public Integer remove(int index) {

        if (index >= size || index < 0 ) {
            return null;
        }

        // vrátit vymazaný prvek
        Integer result = arr[index];

        size--;
        __reAllocate(RealocateOperation.MINUS_CAPACITY, true);
        __reCopy(index);

        changeArr();
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

        if (i1 >= size && i < 0) {
            return null;
        }

        int len = i1 - i;
        Integer[] newArr = new Integer[len]; // little help:  in newArr prepare data for sublist.
        System.arraycopy(arr, i, newArr, 0, len);

        return new MyArrayList(newArr);
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }


}
