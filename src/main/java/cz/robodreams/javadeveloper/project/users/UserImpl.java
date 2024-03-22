package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.common.UtilConst;
import lombok.Getter;

import java.util.Objects;


@Getter
public class UserImpl implements User, UtilConst {


    private Integer id;
    private String gender;

    private String name;
    private String surname;
    private String phone;
    private String email;

    private String city;
    private String street;
    private String streetNumber;
    private String zipCode;


    public UserImpl(Integer id, String gender, String name, String surname, String phone,
                    String email, String city, String street, String streetNumber, String zipCode) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }


    public void show(ShowSubjectItems showItems) {
        System.out.println(getResultShow(showItems));
    }

    public String getShortInfo() {
        return String.format("| %s " + Util.colPurple("%s %s."), gender, name, surname);
    }

    @Override
    public String getResultShow(ShowSubjectItems showItems) {

        String s = "";
        if (showItems == ShowSubjectItems.LONG_FORMAT) {
            s += String.format("| %s " + Util.colCyan("%s %s."), gender, name, surname);
            s += " ".repeat(Math.min(45 - s.length(), s.length()));
            s += String.format("Telefon: " + Util.colYellow("%s") + ", email: " + Util.colYellow("%s") + ".", phone, email);
            s += String.format("\n| Adresa: %s, %s, %s, PSČ %s.", street, streetNumber, city, zipCode);
        } else if (showItems == ShowSubjectItems.INFO) {
            s = String.format("| %s " + Util.colPurple("%s %s."), gender, name, surname);
        }
        return s;
    }


    //
//
//    public void show(Boolean shortLongFormat) {
//        String s = "";
//
//        if (shortLongFormat) {
//        }
//
//        s += String.format("| %s od : %s", colRed("Zapůjčena"), colGreen(sinceWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))));
//        s += String.format(" do : " + colGreen("%s "), untilWhen.format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
//        s += String.format(" %s " + Util.colPurple("%s %s"), user.getGender(), user.getName(), user.getSurname());
//        System.out.println(s);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImpl userImpl = (UserImpl) o;
        return Objects.equals(id, userImpl.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public String getStreetNumber() {
//        return streetNumber;
//    }
//
//    public String getZipCode() {
//        return zipCode;
//    }

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
