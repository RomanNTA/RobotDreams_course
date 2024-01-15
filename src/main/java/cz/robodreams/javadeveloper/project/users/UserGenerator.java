package cz.robodreams.javadeveloper.project.users;

import org.apache.commons.lang3.StringUtils;

public class UserGenerator implements IUserIdentity {

    public Identity giveMePerson(int id ) {

        GENDER gen = (what(1) == 1) ? GENDER.MAN : GENDER.WOMAN;
        String gender = (gen == GENDER.MAN) ? "pan" : "paní";

        String name = (gen == GENDER.MAN)
                ? NAME_OF_MAN[what(COUNT_NAME_OF_MAN - 1)]
                : NAME_OF_WOMAN[what(COUNT_NAME_OF_WOMAN - 1)];

        String surname = (gen == GENDER.MAN)
                ? SURNAME_OF_MAN[what(COUNT_SURNAME_OF_MAN - 1)]
                : SURNAME_OF_WOMAN[what(COUNT_SURNAME_OF_WOMAN - 1)];

        String phone = getRandomPhoneNr();

        String email = StringUtils.stripAccents(name+"."+surname + "@example.org").toLowerCase();

        String city = "Praha";

        String street = STREETS[what(COUNT_STREETS - 1)];

        String streetNumber = String.format("%d/%d",what(800,2000),what(100));

        String zipCode = String.format("%d",what(10000,11199));

        Identity result = new Identity(id,gender,name,surname,phone,email,
                                       city,street,streetNumber,zipCode );

        return result;
    }



    String getRandomPhoneNr() {
        return "602"+ UsefulProc.getRandomIdString(100000,999999);
    }
    private int what(int min,int max) {
        return UsefulProc.getRandomId(min,max);
    }
    private int what(int max) {
        return UsefulProc.getRandomId(0,max);
    }

}
