package cz.robodreams.javadeveloper.project.users;

import cz.robodreams.javadeveloper.project.common.Util;
import org.apache.commons.lang3.StringUtils;

public class People implements IUserConst {

    public UserImpl giveMePerson(int id ) {

        IUserConst.GENDER gen = (what(1) == 1) ? IUserConst.GENDER.MAN : IUserConst.GENDER.WOMAN;
        String gender = (gen == IUserConst.GENDER.MAN) ? "pan" : "paní";

        String name = (gen == IUserConst.GENDER.MAN)
                ? NAME_OF_MAN[what(COUNT_NAME_OF_MAN - 1)]
                : NAME_OF_WOMAN[what(COUNT_NAME_OF_WOMAN - 1)];

        String surname = (gen == IUserConst.GENDER.MAN)
                ? SURNAME_OF_MAN[what(COUNT_SURNAME_OF_MAN - 1)]
                : SURNAME_OF_WOMAN[what(COUNT_SURNAME_OF_WOMAN - 1)];

        String phone = getRandomPhoneNr();

        String email = StringUtils.stripAccents(name+"."+surname + "@example.org").toLowerCase();

        String city = "Švestková Lhota";

        String street = STREETS[what(COUNT_STREETS - 1)];

        String streetNumber = String.format("%d/%d",what(800,2000),what(100));

        String zipCode = String.format("%d",what(10000,11199));

        return UserImpl.builder().id(id).gender(gender).name(name).surname(surname)
                .phone(phone).email(email).city(city).street(street).streetNumber(streetNumber).zipCode(zipCode).build();
    }

    String getRandomPhoneNr() {
        return "602"+ Util.getRandomIdString(100000,999999);
    }
    private int what(int min,int max) {
        return Util.getRandomId(min,max);
    }
    private int what(int max) {
        return Util.getRandomId(0,max);
    }


}
