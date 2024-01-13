package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

public interface FlightConst {

    int FLY_ENTRY_LOGIN = 0;
    int FLY_CIRCUIT_FLIGHT = 1;
    int FLY_LANDING = 2;
    int FLY_EMPTYING = 3;
    int FLY_WAITING = 4;
    int FLY_DEPARTURE = 5;
    int FLY_LOG_OUT = 6;


    String[] COMPANY = {"The Boeing Company", "Airbus Commercial Aircraft"};
    int COMPANY_BOEING = 0;
    int COMPANY_AIRBUS = 1;


    String[] PLANE_TYPE = {"Airliner", "Cargo plane"};
    int PLANE_AIRLINER = 0;
    int PLANE_CARGO = 1;


    String[] CARRIER = {"British Airways", "Lufthansa", "KLM", "Ryan Air", "Vueling", "Wizz Air"};
    String[] CARRIER_ABBR = {"BA", "LUF", "KLM", "RYR", "VUL", "WIZ"};
    int COUNT_OF_CARRIER = CARRIER.length - 1;


    String[] GOODS = {"ocelové konstrukce", "potraviny", "textil", "zásilky/poštovní obsah"};
    int COUNT_OF_GOODS = GOODS.length - 1;

    String[] CITIES = {"Milan", "Paříž", "Barcelona", "Madrid", "Brno", "Praha", "Krakow", "Berlín", "Budapešť", "Vídeň", "Londýn", "Kyjev", "Dubaj", "Hurghada"};
    int COUNT_OF_CITIES = CITIES.length - 1;

    String[] WIND = {"severní", "jižní", "východní", "západní"};
    int COUNT_OF_WIND = WIND.length - 1;


}
