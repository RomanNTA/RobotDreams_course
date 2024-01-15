package cz.robodreams.javadeveloper.project.db;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ATable implements ITable, IConstant, ILoaderCallback {

    private Map<Integer, AColumn> columns = new HashMap<>();
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
        AColumn c = getColumn(col);

        //System.out.println("col = " + col + ", row = " + row + ", value -> " + value);
        switch (c.getType()) {
            case TYPE_STRING -> {
                c.insert((String) value, row);
            }
            case TYPE_INTEGER -> {
                try {
                    NumberFormat format = NumberFormat.getInstance();
                    int i = format.parse((String) value).intValue();
                    c.insert(i, row);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            case TYPE_DATETIME -> {
                c.insert((LocalDateTime) value, row);
            }
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
    public Integer getColumnType(int id) {
        return getColumn(id).getType();
    }


    @Override
    public String getString(int row, int col) {
        return getColumn(col).getValueString(row);
    }

    @Override
    public Integer getInteger(int row, int col) {
        //System.out.println(col + " - " + row);
        return getColumn(col).getValueInteger(row);
    }

    @Override
    public LocalDateTime getLocalDateTime(int row, int col) {
        return getColumn(col).getValueLocalDateTime(row);
    }

    @Override
    public AColumn addColumn(String name, int type, String additional) {
        AColumn ac = new AColumn(name, columns.size(), type, additional);
        columns.put(columns.size(), ac);
        return ac;
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
    public String toString() {
        String s = "ATable{";
        for (int i = 0; i < getColumnCount(); i++) {
            s += "\r\n\t" + getColumn(i).toString();
        }
        s += "\r\n}ATable\r\n";
        return s;
    }
}


//        switch (c.getType()) {
//            case TYPE_STRING -> {
//                c.insert((String) value, row);
//            }
//            case TYPE_INTEGER -> {
//                c.insert((Integer) value, row);
//            }
//            case TYPE_DATETIME -> {
//                c.insert((LocalDateTime) value, row);
//            }
//        }



//    @Override
//    public String getTypeOfSubjectField(int index) {
//        return listTypeOfSubject.get(index);
//    }
//
//    @Override
//    public String getInAdditionOfSubjectField(int index) {
//        return listInAdditinalOfSubjectField.get(index);
//    }
//
//    @Override
//    public int getCountOfSubjectField() {
//        return listNameOfSubject.size();
//    }
//
//    @Override
//    public Boolean isBorrowed(int index) {
//        return listBorrowed.get(index);
//    }


//    @Override
//    public void insertNames(String[] strings) {
//
//
//        for (int i = 0; i < strings.length-1; i++) {
//
//        }


//        System.arraycopy(strings, 0, listNameOfSubject, 0, strings.length - 1);
//        size = listNameOfSubject.size();
//        System.out.println(listNameOfSubject);
//    }
//
//    @Override
//    public void insertTypes(String[] strings) {
//        if (size == strings.length) {
//            throw new RuntimeException("Import dat: Chybný počet typů.");
//        }
//    }
//
//    @Override
//    public void insertAdditional(String[] strings) {
//        if (size == strings.length) {
//            throw new RuntimeException("Import dat: Chybný počet přídavků.");
//        }
//    }
//
//    @Override
//    public Boolean getCountOfSubject() {
//        return null;
//    }
//
//    @Override
//    public String getFields(int row, int column) {
//
////        try {
////            String[] result = linesMap.get(row);
////            return result[column];
////        } catch (NullPointerException e) {
//            return null;
////        }
//    }
//
//    //    @Override
//    public ArrayList<String> toArrayList(Boolean writeTerminal, String delimiter) {
//
//        ArrayList<String> out = new ArrayList<>();
////        linesMap.forEach((key, value) -> {
////            String s = String.join(delimiter, (String[]) value);
////            if (writeTerminal) {
////                System.out.println(s);
////            } else {
////                out.add(s);
////            }
////        });
//        return out;
//    }
//
//    @Override
//    public void insertSubjects(int counter, String[] output) {
////        linesMap.put(counter, output);
//    }

