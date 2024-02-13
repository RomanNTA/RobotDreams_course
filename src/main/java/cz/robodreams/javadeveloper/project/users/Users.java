package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.ASubject;
import cz.robodreams.javadeveloper.project.common.ISubjectAdd;


public class Users extends ASubject<IUser> implements IUsers<IUser>, ISubjectAdd<IUser> {

    @Override
    public void generator(int countOfNewPerson) {
        new UserGenerator(this, countOfNewPerson);
    }

    @Override
    public void show(int id, Boolean shortLongFormat) {
        line();
        get(id).show(shortLongFormat);
    }


    @Override
    public String toString() {
        return "Users{ count of user : " + repository.size() + '}';
    }

}