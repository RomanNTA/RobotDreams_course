package cz.robodreams.javadeveloper.project.balist;


public interface IBufferedArrayList<E> {

    int size();

    E get(int i);

    //    void set(int i, Object value);
    void set(int i, E value);

    Boolean isEmpty();

    void insert(E value, int index);

    Boolean contains(E object);

    Boolean containsAll(E[] collection);


    void add(E value);

    Boolean remove(E value);

    Boolean remove(int index);

    Boolean removeFromTo(int indexFrom, int indexTo);

    Boolean addAll(E... collection);

    void clear();

    int indexOf(E o);

    void insertFromTo(int indexOfInsert, int length, E... objArr);

    IBufferedArrayList subList(int i, int i1);

    Boolean find(E value);

}
