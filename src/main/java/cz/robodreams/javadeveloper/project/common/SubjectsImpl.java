package cz.robodreams.javadeveloper.project.common;

import java.util.ArrayList;
import java.util.List;

public abstract class SubjectsImpl<T> implements Subjects<T>, SubjectAdd<T> {

    protected List<T> repository = new ArrayList<>();

    @Override
    public abstract void generator(int count);

    @Override
    public Boolean add(T value) {

        if (value == null) {
            return false;
        } else {
            synchronized (repository) {
                repository.add(value);
            }
            return true;
        }
    }

    @Override
    public T get(int id) {

        try {
            return repository.get(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer size() {
        return repository.size();
    }

    @Override
    public abstract void show(int id, ShowSubjectItems showItems);

    @Override
    public T getRandomSubject() {
        return get(Util.getRandomId(0, this.size() - 1));
    }
}
