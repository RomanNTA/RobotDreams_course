package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.TerminalColorConst;

import java.util.Objects;

public class AUser extends TerminalColorConst implements IUser {


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


    public AUser(Integer id, String gender, String name, String surname, String phone,
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



    /**
     * @param shortLongFormat ... true = dlouhé
     */
    public void show(Boolean shortLongFormat) {

        String s = String.format("| %s " + colCyan("%s %s."), gender, name, surname);
        s += " ".repeat(Math.min(45 - s.length(), s.length()));

        s += String.format("Telefon: " + colYellow("%s") + ", email: " + colYellow("%s") + ".", phone,email );
        s += String.format("\n| Adresa: %s, %s, %s, PSČ %s.", street, streetNumber, city, zipCode);

        System.out.println(s);
    }

    public String getShortInfo() {
        return String.format("| %s " + colPurple("%s %s."), gender, name, surname);
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
//        s += String.format(" %s " + colPurple("%s %s"), user.getGender(), user.getName(), user.getSurname());
//        System.out.println(s);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AUser aUser = (AUser) o;
        return Objects.equals(id, aUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

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
