package cz.robodreams.javadeveloper.project.db;

import java.time.LocalDateTime;

public interface ITable {


    /**
     * Jmeno sloupce
     */
    String getColumnName(int index);

    /**
     * Počet sloupců
     */
    int getColumnCount();

    /**
     * Počet řádků ... počet předmětu
     */
    Integer getRowsCount(int index);

    /**
     * Typ sloupce
     */
    IConstant.TypeColumn getColumnType(int index);

    /**
     * Vrati obsah bunky
     */
    String getString(int row, int col);

    Integer getInteger(int row, int col);

    LocalDateTime getLocalDateTime(int row, int col);


    AColumn<?,?> addColumn(String name, int type, String additional);

    String getTypeToString(int index);

    void invalidateAndReload();

}
