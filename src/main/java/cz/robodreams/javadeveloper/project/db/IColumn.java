package cz.robodreams.javadeveloper.project.db;

import java.time.LocalDateTime;

public interface IColumn {

    int getId();

//    int getCapacity();
//
//    int setCapacity(int volume);


    String getName();

    void setName(String string);

    int getType();

//    void setType(int type);

    Object getAdditional();

    void setAdditional(Object object);


    void deteteItem(int index);



    String getValueString(int index);

    Integer getValueInteger(int index);

    LocalDateTime getValueLocalDateTime(int index);


    void setValueString(int index, String value);

    void setValueInteger(int index, Integer value);

    void setValueDateTime(int index, LocalDateTime value);


}
