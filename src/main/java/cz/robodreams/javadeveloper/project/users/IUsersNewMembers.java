package cz.robodreams.javadeveloper.project.users;

public interface IUsersNewMembers extends IUsers<IUser> {

    void newMembersAreComing(int countOfNewMembers);

}
