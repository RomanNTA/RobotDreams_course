package cz.robodreams.javadeveloper.project.db;

public interface ILoaderCallback {

    void createColumns(String[] nameOfColumns, String[] typeOfColumns, String[] additionalOfColumns);
    void addRows(String[] rows);

}
