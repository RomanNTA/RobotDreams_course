package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.ISubjectAdd;
import cz.robodreams.javadeveloper.project.db.ATable;

public class UserGenerator implements IUserConst {

    private static Integer counter = 0;

    public UserGenerator(ISubjectAdd<IUser> destination, int countOfNewPerson) {

        if (destination == null) return;

        if (countOfNewPerson > 0 ) {
            for (int i = 0; i < countOfNewPerson; i++) {
                destination.add(counter, new People().giveMePerson(counter));
                counter++;
            }
        }

        // Ukončí až odmítnutí
        if (countOfNewPerson < 0 ) {
            while (destination.add(counter, new People().giveMePerson(counter))) {
                counter++;
            }
        }
    }


}
