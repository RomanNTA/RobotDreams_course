package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

public interface IPlane extends Flight {

    int getAircraftReach();

    int getCountOfCrewMembers();

    int getNumberOfSeats();

    int getMaxSpeed();

    int getType();

    int getCargoCapacity();


    String getCarrierString();

    String getTypePlane();

    String getModel();

    String getManufacturer();

    String getSerialNumber();

    String getFuel();

    void makeNewDestination();

}
