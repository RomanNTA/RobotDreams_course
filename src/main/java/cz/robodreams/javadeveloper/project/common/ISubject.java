package cz.robodreams.javadeveloper.project.common;

public interface ISubject<T> {

    Integer size();


    T get(int id);

    Boolean add(int key, T value);



    void generator(int count);

    T getRandomSubject();



    void line();

    void show(int id, Boolean shortLongFormat);


}
