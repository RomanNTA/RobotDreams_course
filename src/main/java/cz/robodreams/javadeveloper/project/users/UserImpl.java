package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.ShowSubjectItems;
import cz.robodreams.javadeveloper.project.common.Util;
import cz.robodreams.javadeveloper.project.common.UtilConst;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@Builder
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

    @Override
    public void show(ShowSubjectItems showItems) {
        getResultShow(showItems).forEach(System.out::println);
    }

    @Override
    public String getShortInfo() {
        return String.format("| %s " + Util.colPurple("%s %s "), gender, name, surname);
    }

    @Override
    public String getRegisteredClient() {
        return String.format("%s " + Util.colPurple("%s %s") + " - " + Util.colCyan(" %s"), gender, name, surname, email);
    }

    @Override
    public List<String> getResultShow(ShowSubjectItems showItems) {

        List<String> result = new ArrayList<>();
        result.add(Util.getLine());
        String s = "";

        if (showItems == ShowSubjectItems.LONG_FORMAT) {
            s = String.format("| %s " + Util.colCyan("%s %s."), gender, name, surname);
            s += " ".repeat(Math.min(45 - s.length(), s.length()));
            s += String.format("Telefon: " + Util.colYellow("%s") + ", email: " + Util.colYellow("%s") + ".", phone, email);
            result.add(s);
            result.add(String.format("| Adresa: %s, %s, %s, PSČ %s.", street, streetNumber, city, zipCode));
        }
        return result;
    }

}
