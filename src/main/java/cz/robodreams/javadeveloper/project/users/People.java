package cz.robodreams.javadeveloper.project.users;

public class People {

    private static Integer counter = 0;

    public People(IAddPerson destination, int countOfNewPerson) {

        for (int i = 0; i < countOfNewPerson; i++) {
            destination.insertNewUser(counter,(new UserGenerator()).giveMePerson(counter));
            counter++;
        }
    }

}
