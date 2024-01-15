package cz.robodreams.javadeveloper.project.db;

import cz.robodreams.javadeveloper.project.balist.BufferedArrayList;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class AColumn extends BufferedArrayList implements IColumn, IConstant {

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
    private Integer type;

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
    public AColumn(String name, int id, int type, Object additional) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.additional = additional;

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getType() {
        return type;
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public void setName(String setName) {
        this.name = setName;
        if (setName.trim().length() <= 0) {
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
    public Integer getValueInteger(int index) {
        return ((Integer) this.get(index));
    }

    @Override
    public LocalDateTime getValueLocalDateTime(int index) {
        return (LocalDateTime) this.get(index);
    }

    @Override
    public String getValueString(int index) {
        return (String) this.get(index);
    }


    @Override
    public void setValueString(int index, String value) {
        insert((Object) value, index);
    }

    @Override
    public void setValueInteger(int index, Integer value) {
        insert((Object) value, index);
    }

    @Override
    public void setValueDateTime(int index, LocalDateTime value) {
        insert((Object) value, index);
    }


    @Override
    public void deteteItem(int index) {
        insert(null, index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String s = "AColumn{";
        s += "\r\n\tColumn " + id + " : Name= " + name + " : Type= " + typeToString() + ", Rows= " + size();
        s += "\r\n\tArray=[" + getStreamToString() + "];";
        s += "}\r\n";
        return s;

//        return "\r\nADbChain{" +
//                ",\r\nid= '" + id + '\'' +
//                ",\r\nname= '" + name + '\'' +
//                ",\r\ntype= '" + type + '\'' +
//                ",\r\nnadditional= '" + (String) additional + '\'' +
//                "\r\nchain={" + s +
//                "'}\r\n";



//        for (int i = 0; i < size(); i++) {
//            s += String.format("(%d->%s),", i, (String) get(i));
//        }


//        switch (type) {
//
//            case IColumn.TypeSTRING: {
//                for (int i = 0; i < size(); i++) {
//                    s += String.format("(%d->%s),", i, (String) get(i));
//                }
//                break;
//            }


//            case IColumn.TypeSTRING: {
//                for (int i = 0; i < chain.size(); i++) {
//                    s += String.format("(%d->%s),", i, (String) chain.get(i));
//
//                    //"'" + (String) chain.get(i) + "',";
//                }
//                break;
//            }


//            case IColumn.TypeINTEGER: {
//                for (Object val : chain) {
//                    s += "'" + (Integer) val + "'\r\n";
//                }
//                break;
//            }
//            case IColumn.TypeDATETIME: {
//                for (Object val : chain) {
//                    s += "'" + (LocalDateTime) val + "'\r\n";
//                }
//                break;
//            }
//    }


//        return "\r\nADbChain{" +
//                ",\r\nid= '" + id + '\'' +
//                ",\r\nname= '" + name + '\'' +
//                ",\r\ntype= '" + type + '\'' +
//                ",\r\nnadditional= '" + (String) additional + '\'' +
//                "\r\nchain={" + s +
//                "'}\r\n";
    }

    public String typeToString() {
        return TYPE_TO_TEXT[type];
    }




}
