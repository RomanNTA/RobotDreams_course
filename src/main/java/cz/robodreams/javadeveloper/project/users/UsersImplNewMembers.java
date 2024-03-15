package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;

public class UsersImplNewMembers extends UsersImpl implements IUsersNewMembers {


    private Integer countOfNewMembers;

    public void newMembersAreComing(int countOfNewMembers){
        this.countOfNewMembers = countOfNewMembers;
        System.out.println("\t... noví členové knihovny");
        new UserGenerator(this, -1);
    }

    @Override
    public Boolean add(User value) {

        super.add(value);
        countOfNewMembers--;
        value.show(ShowSubjectItems.LONG_FORMAT);

        return countOfNewMembers == 0;
    }



}
