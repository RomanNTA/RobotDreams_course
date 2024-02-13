package cz.robodreams.javadeveloper.project.event;

import cz.robodreams.javadeveloper.project.users.IUser;
import cz.robodreams.javadeveloper.project.users.IUsers;
import cz.robodreams.javadeveloper.project.users.IUsersNewMembers;

public class EventNewUsers extends AEvent {


    private Integer countOfNewPerson;
    private IUsersNewMembers people;


    public EventNewUsers() {
        super(IEventType.NEW_USER, 0,0);
    }

    @Override
    public Boolean run() {
        if (people == null) {
            throw new NullPointerException("Chybí noví členové ... není zájem.");
        }

        if (countOfNewPerson <= 0) {
            return true;
        }

        people.newMembersAreComing(countOfNewPerson);
        return true;
    }

    @Override
    public Boolean isClose() {
        return true;
    }

    public EventNewUsers setCountOfNewPerson(Integer countOfNewPerson) {
        this.countOfNewPerson = countOfNewPerson;
        return this;
    }

    public EventNewUsers setPeople(IUsersNewMembers people) {
        this.people = people;
        return this;
    }




}
