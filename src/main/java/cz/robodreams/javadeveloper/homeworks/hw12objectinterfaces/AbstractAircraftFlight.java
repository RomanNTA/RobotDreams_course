package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

public abstract class AbstractAircraftFlight implements Flight {

    private String flightNumber;
    private String destination;
    private Integer passengerCount;


    public AbstractAircraftFlight(String flightNumber, String destination, Integer passengerCount) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.passengerCount = passengerCount;
    }

    @Override
    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public Integer getPassengerCount() {
        return passengerCount;
    }


    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }

    abstract void makeNewDestination();



}
