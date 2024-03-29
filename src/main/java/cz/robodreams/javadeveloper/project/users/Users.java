package cz.robodreams.javadeveloper.project.users;
import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Subjects;

import java.util.List;
import java.util.Map;

public interface Users extends Subjects<User> {

    List<String> getUsersDriver(ShowSubjectItems showItems);
    Map<Integer, User> getUsersClient();

}
