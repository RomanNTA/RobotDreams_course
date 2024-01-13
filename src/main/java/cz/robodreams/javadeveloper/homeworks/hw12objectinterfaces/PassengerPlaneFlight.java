package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

public class PassengerPlaneFlight extends Plane implements Flight, FlightConst {
    public PassengerPlaneFlight() {

        super(PLANE_AIRLINER);
        setPassengerCount(UsefulProc.getRandomId((int) (getNumberOfSeats() * 0.8), getNumberOfSeats()));
    }

}
