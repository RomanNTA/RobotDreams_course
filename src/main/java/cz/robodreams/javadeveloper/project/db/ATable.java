package cz.robodreams.javadeveloper.project.db;

import cz.robodreams.javadeveloper.project.balist.IBufferedArrayList;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ATable implements ITable, IConstant, ILoaderCallback {

    private Map<Integer, AColumn<?,?>> columns = new HashMap<>();
    private String sourceFile;
    private ILoader loader;

    public ATable(String sourceFile) {
        this.sourceFile = sourceFile;
        invalidateAndReload();
    }

    public int getColumnsCount() {
        return columns.size();
    }

    private AColumn getColumn(int index) {
        if (index >= columns.size() || index < 0) {
            throw new VerifyError("Chyba: Index mimo povolený rozsah.");
        }
        return columns.get(index);
    }

    private void put(int col, int row, Object value) {

        AColumn column = getColumn(col);
        try {
            switch (column.getType()) {
                case TYPE_STRING -> column.setValue(row, (String) value);
                case TYPE_DATETIME -> column.setValue(row, (LocalDateTime) value);
                case TYPE_INTEGER -> {
                    NumberFormat format = NumberFormat.getInstance();
                    column.setValue(row, (Integer) format.parse((String) value).intValue());
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AColumn addColumn(String name, int type, String additional) {

        AColumn column = null;
        switch (type) {
            case IConstant.TYPE_INTEGER -> {
                column = new AColumn<Integer, Integer[]>(Integer.class, Integer[].class,
                        name, columns.size(), TypeColumn.TYPE_INTEGER, additional);
                columns.put(columns.size(), column);
                return column;
            }
            case IConstant.TYPE_STRING -> {
                column = new AColumn<String, String[]>(String.class, String[].class,
                        name, columns.size(), TypeColumn.TYPE_STRING, additional);
                columns.put(columns.size(), column);
                return column;

            }
            case IConstant.TYPE_DATETIME -> {
                column = new AColumn<LocalDateTime, LocalDateTime[]>(LocalDateTime.class, LocalDateTime[].class,
                        name, columns.size(), TypeColumn.TYPE_DATETIME, additional);
                columns.put(columns.size(), column);
                return column;

            }
        }
        return column;
    }

    @Override
    public void invalidateAndReload() {
        columns.clear();
        ILoader loader = new Loader(this, sourceFile);
        loader.loadData();
    }


    @Override
    public void createColumns(String[] nameOfColumns, String[] typeOfColumns, String[] additionalOfColumns) {

        if (nameOfColumns.length != typeOfColumns.length || typeOfColumns.length != additionalOfColumns.length) {
            throw new RuntimeException("Import dat: Create: není stejný počet sloupců.");
        }

        for (int i = 0; i < nameOfColumns.length; i++) {

            if ("STRING".equals(typeOfColumns[i])) {
                AColumn col = addColumn(nameOfColumns[i], IConstant.TYPE_STRING,
                        additionalOfColumns[i]);
            }

            if ("INTEGER".equals(typeOfColumns[i])) {
                AColumn col = addColumn(nameOfColumns[i], IConstant.TYPE_INTEGER,
                        additionalOfColumns[i]);
            }

            if ("DATETIME".equals(typeOfColumns[i])) {
                AColumn col = addColumn(nameOfColumns[i], IConstant.TYPE_DATETIME,
                        additionalOfColumns[i]);
            }
        }
    }

    @Override
    public void addRows(String[] rows) {

        int index = getRowsCount(0);
        for (int col = 0; col < rows.length; col++) {
            put(col, index, rows[col]);
        }
    }



    @Override
    public String getColumnName(int index) {
        return getColumn(index).getName();
    }

    @Override
    public String getTypeToString(int index) {
        return getColumn(index).typeToString();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Integer getRowsCount(int id) {
        return getColumn(id).size();
    }

    @Override
    public TypeColumn getColumnType(int id) {
        return getColumn(id).getType();
    }



    @Override
    public String getString(int row, int col) {
        return (String) getColumn(col).getValue(row);
    }

    @Override
    public Integer getInteger(int row, int col) {
        return (Integer) getColumn(col).getValue(row);
    }

    @Override
    public LocalDateTime getLocalDateTime(int row, int col) {
        return (LocalDateTime) getColumn(col).getValue(row);
    }

    @Override
    public String toString() {
        String s = "ATable{";
        for (int i = 0; i < getColumnCount(); i++) {
            s += "\r\n\t" + getColumn(i).toString();
        }
        s += "\r\n}ATable\r\n";
        return s;
    }
}
