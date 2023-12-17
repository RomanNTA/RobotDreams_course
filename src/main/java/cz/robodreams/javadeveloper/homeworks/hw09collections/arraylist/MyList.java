package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

import java.util.Collection;

public interface MyList {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean add(Integer integer);

    boolean remove(Object o);

    boolean containsAll(Collection<?> collection);

    boolean addAll(Collection<? extends Integer> collection);


    void clear();

    Integer get(int i);

    Integer set(int i, Integer integer);


    Integer remove(int i);

    int indexOf(Object o);

    MyList subList(int i, int i1);

}
