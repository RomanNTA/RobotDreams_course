package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.SubjectAdd;

public class UserGenerator implements IUserConst {

    private static Integer counter = 0;

    public UserGenerator(SubjectAdd<User> destination, int countOfNewPerson) {

        if (destination == null) return;

        if (countOfNewPerson > 0 ) {
            for (int i = 0; i < countOfNewPerson; i++) {
                destination.add( new People().giveMePerson(counter));
                counter++;
            }
        }

        // Ukončí až odmítnutí
        if (countOfNewPerson < 0 ) {
            while (destination.add(new People().giveMePerson(counter))) {
                counter++;
            }
        }
    }


}
