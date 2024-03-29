package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;

import java.util.List;

public interface User {

    void show(ShowSubjectItems showItems);
    List<String> getResultShow(ShowSubjectItems showItems);
    String getShortInfo();

    String getRegisteredClient();

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
