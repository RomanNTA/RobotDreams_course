package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

public interface Flight {

    /**
     * Vytvořte rozhraní Flight, které bude reprezentovat lety na letišti. Toto rozhraní by mělo obsahovat metody:
     * String getFlightNumber(): Vrátí číslo letu.
     * String getDestination(): Vrátí cílové místo letu.
     * int getPassengerCount(): Vrátí počet cestujících na letu.
     */

    String getFlightNumber();

    String getDestination();

    Integer getPassengerCount();

}
