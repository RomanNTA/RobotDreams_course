package cz.robodreams.javadeveloper.project.db;

import cz.robodreams.javadeveloper.project.balist.BufferedArrayList;

import java.time.LocalDateTime;
import java.util.Objects;

public class AColumn<T, E> extends BufferedArrayList<E> implements IColumn<T>, IConstant {

    /**
     * id identifikátor a zpětný odkaz na pozici v rootu
     * neměnné !
     */
    private int id;

    /**
     * jméno sloupce
     */
    private String name;

    /**
     * typ sloupce podle konstant
     */
    private TypeColumn type;

    /**
     * přídavek navíc pro uložení dalšího zpracování
     * (pro půjčovnu se použije k uřčení v kterém sloupci je cena subjektu a kde je cena zálohy)
     */
    private Object additional;


    /**
     * Konstruktor vytvoří hlavní pole
     *
     * @param id - ID okbjektu
     */

    //, int id, int type
    public AColumn(Class type1, Class type2, String name, int id, TypeColumn strType, Object additional) {

        super(type2);
        this.id = id;
        this.name = name;
        this.type = strType;
        this.additional = additional;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public TypeColumn getType() {
        return type;
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public void setName(String setName) {
        this.name = setName;
        if (setName.trim().length() == 0) {
            this.name = "<unknown>";
        }
    }


    @Override
    public Object getAdditional() {
        return additional;
    }
    @Override
    public void setAdditional(Object object) {
        this.additional = object;
    }


    @Override
    public void setValue(int index, T value) {
        insert((E) value, index);
    }
    @Override
    public T getValue(int index) {
        return ((T) this.get(index));
    }



    @Override
    public void deteteItem(int index) {
        insert(null, index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String typeToString() {
        return TYPE_TO_TEXT[type.ordinal()];
    }


}
