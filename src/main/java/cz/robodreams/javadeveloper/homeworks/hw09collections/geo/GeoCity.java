package cz.robodreams.javadeveloper.homeworks.hw09collections.geo;

import java.util.*;
import java.util.HashSet;

public class GeoCity {

    /**
     * This is resenting city and connection to another city key  -> is a city value -> Set<String> is connected city to key city
     * This will represent one way connections.
     * <p>
     * For example {key} -> {connections} :
     * Prague -> Brno, Plzen
     * <p>
     * Means that exist road from Prague to brno and from Prague to Plzen. But do not exist roud from Brno to prague
     * If we would like to have both direction than  data should be:
     * Prague -> Brno,Plzen
     * Plzen -> Prague
     * Brno -> Prague
     */
    Map<String, Set<String>> cityMap = new HashMap<>();


    public GeoCity() {
        System.out.println();
        System.out.println("-----------------------------------------------------");
    }


    /**
     * Register new city if is not present
     *
     * @param city
     */
    public void registerCity(String city) {

        cityMap.put(city, new HashSet<>());
        System.out.println("Registrace mesta .............. " + city);

    }

    private void insertConnect(String cityFrom, String cityTo) {

        if (cityMap.containsKey(cityFrom)) {
            cityMap.get(cityFrom).add(cityTo);
        }

    }


    /**
     * Connect two city (bidirectional) . If one or both cities are not registered, then nothing
     *
     * @param cityFrom
     * @param cityTo
     */
    public void connectCity(String cityFrom, String cityTo) {

        insertConnect(cityFrom, cityTo);
        insertConnect(cityTo, cityFrom);
        System.out.println("Propojena mesta ............... " + cityFrom + " <-> " + cityTo);

    }

    /**
     * Return city direct connections. If no connections exist, then return empty Set
     *
     * @param city
     * @return city direct connections. If no connections exist, then return empty Set
     */
    public Set<String> getCityConnections(String city) {

        return cityMap.getOrDefault(city, Collections.emptySet());
    }

    /**
     * Remove connection between city. If one or both cities are not registered, then nothing
     *
     * @param cityFrom
     * @param cityTo
     */
    public void removeConnections(String cityFrom, String cityTo) {

        if (cityMap.containsKey(cityFrom)) {
            cityMap.get(cityFrom).remove(cityTo);
        } else {
            System.out.println("Chyba. Pokus o odstranění neexistujiciho spojeni. ( " + cityFrom + " - " + cityTo + ")");
            return;
        }

        if (cityMap.containsKey(cityTo)) {
            cityMap.get(cityTo).remove(cityFrom);
        } else {
            System.out.println("Chyba. Pokus o odstranění neexistujiciho spojeni. ( " + cityFrom + " - " + cityTo + ")");
            return;
        }

        System.out.println("Zruseni propojeni mesta ....... " + cityFrom + " <-> " + cityTo);

    }

    /**
     * Remove city. Do not forget to remove it from all connections If city is not registered, then nothing
     *
     * @param city
     */
    public void removeCity(String city) {

        cityMap.remove(city);

        for (Map.Entry<String, Set<String>> cities : cityMap.entrySet()) {

            System.out.println("Vymaz mesta ................... " + cities.getKey());
            if (cities.getValue().contains(city)) {
                cities.getValue().remove(city);
            }
        }
    }

    /**
     * Return all possible destination from destination city
     * <p>
     * For example we have   {key} -> {connections} Praha -> Brno, Plzen Plzen -> Praha Brno -> Praha Plzen -> Karlovy Vary, Domazlice, Praha Karlovy
     * Vary -> Plzen Domazlice-> Pisek, Ceske Budejovice, Plzen Olomouc -> Opava
     * <p>
     * then result all possible destination from Prague would be: Brno, Plzen, Karlovy Vary, Domazlice, Pisek, Ceske Budejovice
     * <p>
     * if no connections return empty set.
     *
     * @param city
     * @return
     */
    public Set<String> getAllPossibleDestinationFromCity(String city) {

        if (!cityMap.containsKey(city)) {
            return null;
        }

        // inicializace bufferu
        Stack<String> buffer = new Stack<>();

        // Vysledny seznam
        Set<String> result = new HashSet<>();
        result.add(city);

        // Naplneni bufferu mesty, ktere primarne patri k "city"
        buffer.addAll(0, cityMap.get(city));

        // hlavni cyklus
        while (buffer.size() > 0) {

            // Vyber z bufferu
            String sCity = buffer.pop();
            result.add(sCity);

/*
            // Pokud neco najdes, pak to pridej do bufferu
            // 1 verze
            Iterator<String> tmpIterator = cityMap.get(sCity).iterator();
            while (tmpIterator.hasNext()) {
                String tempStr = tmpIterator.next();
                if (!buffer.contains(tempStr) && !result.contains(tempStr)) {
                    System.out.println("pridavam do bufferu - " + tempStr);
                    buffer.add(tempStr);
                }
            }
*/

            // Pokud neco najdes, pak to pridej do bufferu
            // 2 verze
            for (String tempStr : cityMap.get(sCity)) {
                if (!buffer.contains(tempStr) && !result.contains(tempStr)) {
                    System.out.println("pridavam do bufferu - " + tempStr);
                    buffer.add(tempStr);
                }
            }

        }

        if (result.size() == 1) {
            System.out.println("Z '" + city + "' nemůžeme vycestovat. Indiani vytrhali koleje ;-)");
            result.clear();
            return result;

        } else {

            String t = "";
            for (String s : result) {
                t += s + ",";
            }

            System.out.println("Z '" + city + "' můžeme cestovat do " + t);
            return result;

        }

    }

}
