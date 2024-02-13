package cz.robodreams.javadeveloper.project.users;

public class UsersNewMembers extends Users implements IUsersNewMembers {


    private Integer countOfNewMembers;

    public void newMembersAreComing(int countOfNewMembers){
        this.countOfNewMembers = countOfNewMembers;
        System.out.println("\t... noví členové knihovny");
        new UserGenerator(this, -1);
    }

    @Override
    public Boolean add(int key, IUser value) {

        super.add(key, value);
        countOfNewMembers--;
        value.show(true);

        return countOfNewMembers == 0;
    }



}
