package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

import java.util.Random;

public class Plane extends AbstractAircraftFlight implements IPlane, Flight, FlightConst {



    /**
     * Další vlastnosti
     */

    private int aircraftReach;
    private int countOfCrewMembers;
    private int numberOfSeats;
    private int maxSpeed;
    private int type;
    private int cargoCapacity;
    private int carrier;

    private String typePlane;
    private String model;
    private String manufacturer;
    private String serialNumber;
    private String fuel;
    private int company;


    public Plane(int type) {
        super("", "", 0);
        this.type = type;
        company = which(2);
        carrier = which(COUNT_OF_CARRIER);
        makeAnAirPlane();
        makeNewDestination();
    }


    private void makeAnAirPlane() {

        int manu = which(1);

/**
 *  Společnost Boeing
 */
        if (manu == COMPANY_BOEING) {

/**
 *  Linková dopravní letadla
 */
            if (type == PLANE_AIRLINER) {

                int countOfPlane = 3;

                switch (which(1, countOfPlane)) {
                    case 1: {
                        countOfCrewMembers = 2;
                        model = "Boeing 747-400";
                        aircraftReach = 13490;
                        maxSpeed = 1047;
                        numberOfSeats = 416;
                        break;
                    }

                    case 2: {
                        countOfCrewMembers = 2;
                        model = "Boeing 777-200";
                        aircraftReach = 9700;
                        maxSpeed = 892;
                        numberOfSeats = 313;
                        break;
                    }

                    case 3: {
                        countOfCrewMembers = 2;
                        model = "Boeing 777-300ER";
                        aircraftReach = 13600;
                        maxSpeed = 892;
                        numberOfSeats = 550;
                        break;
                    }
                    default:
                        throw new AssertionError("Chyba BOING  Dopravní letadlo.");
                }
                typePlane = PLANE_TYPE[PLANE_AIRLINER];
                cargoCapacity = 0;
            }
/**
 *  Nákladní letadla
 */
            if (type == PLANE_CARGO) {

                int countOfPlane = 4;
                switch (which(1, countOfPlane)) {
                    case 1: {
                        countOfCrewMembers = 2;
                        model = "Boeing 747-400";
                        aircraftReach = 13490;
                        maxSpeed = 1047;
                        numberOfSeats = 12;
                        cargoCapacity = 89500;
                        break;
                    }

                    case 2: {
                        countOfCrewMembers = 2;
                        model = "Boeing 777";
                        aircraftReach = 9700;
                        maxSpeed = 892;
                        numberOfSeats = 12;
                        cargoCapacity = 50500;
                        break;
                    }

                    case 3: {
                        countOfCrewMembers = 2;
                        model = "Boeing 777ER";
                        aircraftReach = 13600;
                        maxSpeed = 892;
                        numberOfSeats = 12;
                        cargoCapacity = 113400;
                        break;
                    }
                    case 4: {
                        countOfCrewMembers = 2;
                        model = "Boeing 747-400 Large Cargo Freighter (LCF)";
                        aircraftReach = 13600;
                        maxSpeed = 892;
                        numberOfSeats = 12;
                        cargoCapacity = 113400;
                        break;
                    }
                    default:
                        throw new AssertionError("Chyba BOING  CArgo.");
                }
                typePlane = PLANE_TYPE[PLANE_CARGO];
            }
            manufacturer = COMPANY[COMPANY_BOEING];
        }


/**
 *  Společnost Airbus
 */
        if (manu == COMPANY_AIRBUS) {

/**
 *  Linková dopravní letadla
 */

            if (type == PLANE_AIRLINER) {

                int countOfPlane = 3;
                switch (which(1, countOfPlane)) {
                    case 1: {
                        countOfCrewMembers = 2;
                        model = "Airbus A350-900";
                        aircraftReach = 15000;
                        maxSpeed = 903;
                        numberOfSeats = 247;
                        break;
                    }
                    case 2: {
                        countOfCrewMembers = 3;
                        model = "Airbus A300";
                        aircraftReach = 9800;
                        maxSpeed = 960;
                        numberOfSeats = 247;
                        break;
                    }
                    case 3: {
                        countOfCrewMembers = 2;
                        model = "Airbus A380";
                        aircraftReach = 15200;
                        maxSpeed = 945;
                        numberOfSeats = 525;
                        break;
                    }
                    default:
                        throw new AssertionError("Chyba AIRBUS  Dopravní letadlo.");
                }
                cargoCapacity = 0;
                typePlane = PLANE_TYPE[PLANE_AIRLINER];
            }


/**
 *  Nákladní letadla
 */
            if (type == PLANE_CARGO) {

                int countOfPlane = 3;

                switch (which(1, countOfPlane)) {
                    case 1: {
                        countOfCrewMembers = 2;
                        model = "Airbus A350-900";
                        aircraftReach = 15000;
                        maxSpeed = 903;
                        numberOfSeats = 12;
                        cargoCapacity = 53000;
                        break;
                    }
                    case 2: {
                        countOfCrewMembers = 3;
                        model = "Airbus A300";
                        aircraftReach = 11000;
                        maxSpeed = 960;
                        numberOfSeats = 12;
                        cargoCapacity = 37495;
                        break;
                    }
                    case 3: {
                        countOfCrewMembers = 4;
                        model = "Airbus A300-600ST Beluga XL";
                        aircraftReach = 4260;
                        maxSpeed = 737;
                        numberOfSeats = 0;
                        cargoCapacity = 47500;
                        break;
                    }
                    default:
                        throw new AssertionError("Chyba AIRBUS  CARGO");
                }
                typePlane = PLANE_TYPE[PLANE_CARGO];
            }
            manufacturer = COMPANY[COMPANY_AIRBUS];
        }
        serialNumber = model + "[" + UsefulProc.getRandomIdString(14) + "]";
        fuel = "Jet A-1";
    }

    private int which(int max) {
        Random rand = new Random();
        return (int) (rand.nextInt(max + 1));
    }

    private int which(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }


    @Override
    public void makeNewDestination() {
        setDestination(CITIES[which(COUNT_OF_CITIES)]);
        String s = String.format("%s-%s", CARRIER_ABBR[carrier], UsefulProc.getRandomIdString(100, 9999));
        setFlightNumber(s);
    }

    /**
     * GETTER-SETTER
     */
    @Override
    public String getCarrierString() {
        return CARRIER[carrier];
    }

    @Override
    public int getAircraftReach() {
        return aircraftReach;
    }

    @Override
    public int getCountOfCrewMembers() {
        return countOfCrewMembers;
    }

    @Override
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String getTypePlane() {
        return typePlane;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String getFuel() {
        return fuel;
    }

}
