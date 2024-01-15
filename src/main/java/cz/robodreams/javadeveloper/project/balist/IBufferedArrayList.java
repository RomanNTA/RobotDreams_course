package cz.robodreams.javadeveloper.project.balist;


public interface IBufferedArrayList {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean add(Object value);

    boolean remove(Object o);

    Boolean remove(int index);

    //boolean containsAll(Collection<?> collection);
    boolean containsAll(Object... objArr);

    //boolean addAll(Collection<? extends Object> collection);
    boolean addAll(Object... objArr);

    void clear();

    Object get(int i);

    Object set(int i, Object value);

    int indexOf(Object o);

    IBufferedArrayList subList(int i, int i1);

    void insertFromTo(int indexOfInsert, int length, Object... objArr);

    void insert(Object o, int index);

    void setCalc(BufferedArrayListCalculator calc);

    Boolean removeFromTo(int indexFrom, int indexTo);

    BufferedArrayListCalculator getCalc();

    String getStreamToString();

}
