package cz.robodreams.javadeveloper.project.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ASubject<T> implements ISubject<T> {

    //protected Map<Integer, T > repository = new HashMap<>();
    //protected Map<T > repository = new HashMap<>();
    protected List<T > repository = new ArrayList<>();

    @Override
    public abstract void generator(int count);

    @Override
    public Boolean add(int key, T value) {
        if (value != null) {
            //repository.put(key, value);
            repository.add(value);
        }
        return true;
    }

    @Override
    public T get(int id) {

//        if (repository.containsKey(id)) {
//            return repository.get(id);
//        }
//        return null;
        if (id > repository.size() || id < 0) {
            throw new ArrayIndexOutOfBoundsException("Chybný index.");
        }
        return repository.get(id);
    }

    @Override
    public Integer size() {
        return repository.size();
    }

    @Override
    public void line() {
        System.out.println("+" + "-".repeat(90));
    }

    @Override
    public abstract void show(int id, Boolean shortLongFormat);

    @Override
    public T getRandomSubject(){
        return get(UsefulProc.getRandomId(0,this.size()-1));
    }
}
