package cz.robodreams.javadeveloper.project.users;

public interface IUser {

    void show(Boolean shortLongFormat);

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
