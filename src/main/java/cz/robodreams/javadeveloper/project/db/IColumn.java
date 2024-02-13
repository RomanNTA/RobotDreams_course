package cz.robodreams.javadeveloper.project.db;

public interface IColumn<T> {

    int getId();

    IConstant.TypeColumn getType();

    void deteteItem(int index);


    String getName();
    void setName(String string);


    Object getAdditional();
    void setAdditional(Object object);



    T getValue(int index);
    void setValue(int index, T value);


}
