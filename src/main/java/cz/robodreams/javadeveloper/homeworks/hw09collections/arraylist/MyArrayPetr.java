package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

import java.util.Arrays;
import java.util.Collection;

public class MyArrayPetr implements MyList, InnerAccessToArrayList {

    Integer[] arr;
    int size;

    public MyArrayPetr() {
        arr = new Integer[InnerAccessToArrayList.INIT_CAPACITY];
        size = 0;
    }

    public MyArrayPetr(Integer[] arr) {
        this.arr = arr;
        this.size = arr.length - 1;
    }
// To mě taky hned napadlo ... ale pak jsem si uvědomil to nafukování.
// Je to funkční, jednoduché ... ale mimo zadání.




    @Override
    public int getAllocatedCapacity() {
        return arr.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public boolean add(Integer integer) {
        grow(1);
        arr[size++] = integer;
        return true;
    }

    private void grow(int growAtLeastBy) {

//        if (getAllocatedCapacity() == size) {

// Tady to je částečně funkční. Je to dělané pro variantu "add" ... tj. přičítáš po "1"
// a když se dostaneš na poslední pole, tak víš, že musíš přepočítat a zvýšit.
// Pokud máš požadavek na growAtLeastBy = 5 a getAllocatedCapacity() = 2 a ty máš size např. "0" na počátku,
// tak Ti to přeteče, protože alokované máš jen 2 kousky a kopíruješ 5.

// To již jde.
        if (getAllocatedCapacity() == size || growAtLeastBy + size > getAllocatedCapacity() ) {


            double load = arr.length * (InnerAccessToArrayList.INCREASE_CAPACITY_LOAD_FACTOR / 100.0);
            int increaseBy = Math.max((int) load, growAtLeastBy);
            Integer[] newArr = new Integer[arr.length + increaseBy];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }

    }

    @Override
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i == -1) {
            return false;
        }
        remove(i);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return collection.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {
        Integer[] array = collection.toArray(new Integer[0]);

// Tady máš problém, protože máš velikost jen array.length a chybí velikost size
//      grow(array.length);
        grow(array.length + size);
// a tady to spadne ... přeteče to
        System.arraycopy(array, 0, this.arr, size, array.length);

// Tady se neshodnem, protože z mého pohledu nerespektuješ zadání.
// ... a zadání je nafouknout ... což znamená přepočítat poměr.
// Ty to jen navýšiš, ale nenafukuješ o zadané procenta.
// Je to funkční, chápu ... ale mimo zadání.
        this.size += array.length;


        return false;
    }


    @Override
    public void clear() {
        arr = new Integer[InnerAccessToArrayList.INIT_CAPACITY];
        size = 0;
    }

    @Override
    public Integer get(int i) {
        this.checkIndex(i);
        return arr[i];
    }

    @Override
    public Integer set(int i, Integer integer) {
        this.checkIndex(i);
        Integer prevValue = arr[i];
        arr[i] = integer;
        return prevValue;
    }

    @Override
    public Integer remove(int i) {
        this.checkIndex(i);
        int newSize = this.size - 1;
        Integer value = arr[i];

// -------------------------------
        if (newSize == i) {
            arr[i] = null;
            this.size--;
            return value;
        }
// Pokud budeš ubírat od konce, po jednom, a nepřepočítáváš velikost,
// tak nikdy nesnížíš velikost, ikdyž bude size = 0
// Dělal jsem zkoušku přes random i kontinuální a Tobě to vycházelo chaoticky.
// A to jen proto, že se random strefoval do konečného čísla a Tobě se to nepřepočte.



        System.arraycopy(arr, i + 1, arr, i, this.size - i - 1);
// Tady mě to dostalo ... nevím proč, ale předpokládal jsem, že odkazovat se dvakrát na sebe nejde,
// už jen asi proto, že nemůže realokovat pamět.
// To abych si úkol předělal :/ :(


        this.size--;
        arr[size] = null;

        decreaseSizeIfNeeded();

        return value;
    }

    private void decreaseSizeIfNeeded() {
        int decision = (int) (arr.length * InnerAccessToArrayList.DECREASE_CAPACITY_DECISION_FACTOR / 100.0);
        if (this.size <= decision) {
            int newSize = (int) (arr.length * DECREASE_CAPACITY_FACTOR / 100.0);
            Integer[] newArr = new Integer[newSize];
            System.arraycopy(arr, 0, newArr, 0, this.size);
            arr = newArr;
        }
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= size) {
            throw new ArrayIndexOutOfBoundsException("Out ouf bound exception");
        }
    }

    @Override
    public int indexOf(Object o) {
        if (!(o instanceof Integer)) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (arr[i] != null && arr[i].equals(o)) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public MyList subList(int i, int i1) {

// Toto je nefunkční, protože je předpoklad (i z Tvého dalšího kodu ... odčítáš i1-i ),
// že pozice od-do ... takže Ti to vždy vyhodí vyjímku a nebo kousek níže přeteče.
//        if (i1 > i) {
//            throw new IllegalStateException("index must be in order from to");
//        }
        if (i1 < i) {
            throw new IllegalStateException("index must be in order from to");
        }

        this.checkIndex(i);
        this.checkIndex(i1);
        Integer[] newArr = new Integer[i1 - i];
        System.arraycopy(arr, i, newArr, 0, i1 - i);
        return new MyArrayList(newArr);
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

}