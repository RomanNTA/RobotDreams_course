package cz.robodreams.javadeveloper.project.users;

import java.util.HashMap;

public class Users implements IUsers, IAddPerson {

    private HashMap<Integer, Identity> users = new HashMap<>();

    public void addNewUsers(int countOfNewPerson) {
        new People(this, countOfNewPerson);
    }

    public void insertNewUser(int key, Identity value) {
        users.put(key, value);
    }

    public Identity getPerson(int id) {

        if (id > users.size() || id < 0) {
            throw new ArrayIndexOutOfBoundsException("User: Chybný index.");
        }
        return users.get(id);
    }


    public Integer getCountPerson() {
        return users.size();
    }

    public void line() {
        System.out.println("+" + "-".repeat(90));
    }

    public void show(int id, Boolean shortLongFormat) {
        line();
        getPerson(id).show(shortLongFormat);
    }


    @Override
    public String toString() {

        //users.forEach((k, v) -> System.out.println(k + " - " + v.toString()));
        return "Users{" +
                //"users=" + users +
                '}';
    }
}
