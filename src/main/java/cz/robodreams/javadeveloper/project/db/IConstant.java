package cz.robodreams.javadeveloper.project.db;

public interface IConstant {

    enum TypeColumn {
        TYPE_STRING,
        TYPE_INTEGER,
        TYPE_DATETIME
    }

    int TYPE_STRING = 1;
    int TYPE_INTEGER = 2;
    int TYPE_DATETIME = 3;

    String[] TYPE_TO_TEXT = {"?","string", "integer", "datum a čas"};
    int COUNT_OF_TYPE_TO_TEXT = TYPE_TO_TEXT.length - 1;
}
