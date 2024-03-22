package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;

public interface User {

    void show(ShowSubjectItems showItems);
    String getResultShow(ShowSubjectItems showItems);

    Integer getId();

    String getGender();

    String getName();

    String getSurname();

    String getPhone();

    String getEmail();

    String getCity();

    String getStreet();

    String getStreetNumber();

    String getZipCode();


}
