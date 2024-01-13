package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ControlTower implements IControlTower, FlightConst {


    @Override
    public void nextDay() {

        Queue<IPlane> entryLogin = new LinkedList<>();
        Queue<IPlane> circuitFlight = new LinkedList<>();
        IPlane landing = null; // přistává jen jeden
        Queue<IPlane> emptying = new LinkedList<>();
        Queue<IPlane> waiting = new LinkedList<>();
        IPlane departure = null; // odlétá jen jeden;
        Queue<IPlane> logPut = new LinkedList<>();

        int countOfPlanePerDay = 20;
        int i;
        Boolean isEmpty = true;

        while (countOfPlanePerDay > 0 || isEmpty) {

            // Rozloučení s letadlem, které se předává jiném kontrolnímu centru
            while (logPut.size() > 0) {
                IPlane p = logPut.poll();
                if (p != null) {
                    fly(FLY_LOG_OUT, p);
                }
            }

            // Přidáme letadlo, které odlétlo
            if (departure != null) {
                fly(FLY_DEPARTURE, departure);
                logPut.add(departure);
                departure = null;
            }

            // Ostatní čekají na odlet
            if (waiting.size() > 0) {
                IPlane p = waiting.poll();
                if (p != null) {
                    departure = p;
                    fly(FLY_WAITING, p);
                }
            }

            // jen jedno letadlo může odlétat
            if (waiting.size() > 0) {
                IPlane p = waiting.poll();
                if (p != null) {
                    departure = p;
                    fly(FLY_DEPARTURE, p);
                }
            }

            // Probíhá odbavení všech letadel
            while (emptying.size() > 0) {
                IPlane p = emptying.poll();
                if (p != null) {
                    waiting.add(p);
                    fly(FLY_EMPTYING, p);
                }
            }

            // jen jedno letadlo může přistávat
            if (circuitFlight.size() > 0) {
                IPlane p = circuitFlight.poll();
                if (p != null) {
                    landing = p;
                    fly(FLY_LANDING, landing);
                }
            }

            // Přidáme letadlo, které přistálo na
            if (landing != null) {
                emptying.add(landing);
                landing = null;
            }

            // Navedení 1 - 3 letadel na okruh
            if (entryLogin.size() > 0) {
                i = howMuch(0, 3);
                while (i-- > 0 && entryLogin.size() > 0) {
                    IPlane p = entryLogin.poll();
                    if (p != null) {
                        circuitFlight.add(p);
                        fly(FLY_CIRCUIT_FLIGHT, p);
                    }
                }
            }

            // Přihlasení 1 - 3 letadel na okruh
            if (entryLogin.size() < 2) {
                i = howMuch(0, 3);
                while (i-- > 0 && countOfPlanePerDay > 0) {
                    // Linka nebo cargo ?
                    IPlane p = (howMuch(0, 1) == 0) ? new PassengerPlaneFlight() : new CargoPlaneFlight();
                    entryLogin.add(p);
                    fly(FLY_ENTRY_LOGIN, p);
                    // System.out.println("--- LETADLO = " + countOfPlanePerDay);
                    countOfPlanePerDay--;
                }
            }

            int lan = (landing != null) ? 1 : 0;
            int dep = (departure != null) ? 1 : 0;
            i = entryLogin.size() + circuitFlight.size() + emptying.size() + waiting.size() + logPut.size() + dep + lan;

            isEmpty = (i != 0);

            System.out.printf("\tPočet= %d;Vstup= %d, na okruhu= %d, přistání= %d, odbavení= %d, cekani= %d, odlet= %d, výstup= %d\r\n", countOfPlanePerDay, entryLogin.size(), circuitFlight.size(), lan, emptying.size(), waiting.size(), dep, logPut.size());
            System.out.println("---------------------------------------------------------------------------------------------");

        }

        for (i = 0; i < 4; i++) {
            System.out.println();
        }
    }

    private void fly(int status, IPlane p) {

        if (p == null) {
            throw new NullPointerException("Chyba ... null na vstupu");
        }

        List<String> result = new ArrayList();
        switch (status) {
            case FLY_ENTRY_LOGIN -> {
                result.add(String.format("Letadlo společnosti %s, let %s na pravidelné lince '%s - Ostrava',\r\nvstoupilo do vzdušného prostoru letiště Ostrava Mošnov.", p.getCarrierString(), p.getFlightNumber(), p.getDestination()));
                result.add(String.format("\tŘízení letového provozu Ostrava Mošnov", p.getFlightNumber()));
                result.add(String.format("\tLetu %s pokračujte v kurzu %s na okruh.", p.getFlightNumber(), UsefulProc.getRandomIdString(0, 359)));

                if (p.getType() == PLANE_AIRLINER) {
                    result.add(String.format("Dopravní letadlo %s společnosti %s, let %s\r\ns %d cestujícími na palubě je navedeno na okruh.", p.getModel(), p.getCarrierString(), p.getFlightNumber(), p.getPassengerCount()));
                }

                if (p.getType() == PLANE_CARGO) {
                    result.add(String.format("Nákladní letadlo %s %s společnosti %s, let s obsahem\r\n'%s' na palubě je navedeno na okruh", p.getModel(), p.getCarrierString(), p.getFlightNumber(), ((CargoPlaneFlight) p).getGoodsOnBoard()));
                }
            }
            case FLY_CIRCUIT_FLIGHT -> {
                result.add(String.format("\t%s pokračujte po okruhu.", p.getFlightNumber()));
            }
            case FLY_LANDING -> {
                result.add(String.format("\tMošnov Airport k letu %s. Proveďte přistání na ranveji 03A.", p.getFlightNumber()));

                result.add(String.format("\tVítr %s, %d m/s ", WIND[howMuch(0, COUNT_OF_WIND)], UsefulProc.getRandomId(2, 7)));

                result.add(String.format("\t\t* %s. přistál za normálních podmínek.", p.getFlightNumber()));

                result.add(String.format("\t\t* %s výrobce %s SN:%s", p.getModel(), p.getManufacturer(), p.getSerialNumber()));

                result.add(String.format("\t\t* Dolet: %d km. Palivo: '%s'. Cestovní rychlost: %d km/h", p.getAircraftReach(), p.getFuel(), p.getMaxSpeed()));

                if (p.getType() == PLANE_AIRLINER) {
                    result.add(String.format("\t%s. Vítejte v Ostravě. Pojíždějte na stojánek %02d.", p.getFlightNumber(), UsefulProc.getRandomId(1, 7)));
                }
                if (p.getType() == PLANE_CARGO) {
                    result.add(String.format("\t%s. Vítejte v Ostravě. Pojíždějte na rampu A-sever.", p.getFlightNumber()));
                }
            }
            case FLY_EMPTYING -> {
                if (p.getType() == PLANE_AIRLINER) {
                    result.add(String.format("Probíhá odbavení cestujících letu %s.", p.getFlightNumber()));
                }
                if (p.getType() == PLANE_CARGO) {
                    result.add(String.format("Probíhá vykládka zboží letu %s.(%s)", p.getFlightNumber(), p.getCarrierString()));
                }
            }
            case FLY_WAITING -> {
                String s = p.getFlightNumber();
                p.makeNewDestination();
                result.add(String.format("\t%s byla přidělená identifikace '%s'. Váš nový cíl je '%s'. Vyčkejte povolení rolovat.", s, p.getFlightNumber(), p.getDestination()));

            }
            case FLY_DEPARTURE -> {
                result.add(String.format("\tLet %s. 'Ostrava - %s'. Povolení k odletu z ranveje 03.", p.getFlightNumber(), p.getDestination()));
            }
            case FLY_LOG_OUT -> {
                result.add(String.format("\t%s. Pokračujte v kurzu %s. ", p.getFlightNumber(), UsefulProc.getRandomIdString(0, 359)));
                result.add(String.format("\tPřepněte na kanál %s. Pokračujte na kurzu %s.", UsefulProc.getRandomIdString(10, 100), UsefulProc.getRandomIdString(0, 360)));
                result.add("\tŘízení letového provozu Ostrava se loučí. Mějte dobrý let. ");
            }
        }
        result.forEach(System.out::println);
        System.out.println();
    }

    private int howMuch(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }


}
