package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

public class TheInternationalAirport {

    public static void main(String[] args) {
        IControlTower tower = new ControlTower();
        tower.nextDay();
    }

}
