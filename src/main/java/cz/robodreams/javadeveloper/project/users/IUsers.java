package cz.robodreams.javadeveloper.project.users;

public interface IUsers {


    void addNewUsers(int countOfNewPerson);

    void insertNewUser(int key, Identity value);

    Identity getPerson(int id);

    Integer getCountPerson();

    void show(int id, Boolean shortLongFormat);

    void line();


}
