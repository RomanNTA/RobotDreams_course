package cz.robodreams.javadeveloper.homeworks.hw09collections.geo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GeoCity {

    /**
     * This is resenting city and connection to another city key  -> is a city value -> Set<String> is connected city to key city
     * This will represent one way connections.
     *
     * For example {key} -> {connections} :
     * Prague -> Brno, Plzen
     *
     * Means that exist road from Prague to brno and from Prague to Plzen. But do not exist roud from Brno to prague
     * If we would like to have both direction than  data should be:
     * Prague -> Brno,Plzen
     * Plzen -> Prague
     * Brno -> Prague
     */
    Map<String, Set<String>> cityMap = new HashMap<>();

    /**
     * Register new city if is not present
     *
     * @param city
     */
    public void registerCity(String city) {
    }

    /**
     * Connect two city (bidirectional) . If one or both cities are not registered, then nothing
     *
     * @param cityFrom
     * @param cityTo
     */
    public void connectCity(String cityFrom, String cityTo) {
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

    }

    /**
     * Remove city. Do not forget to remove it from all connections If city is not registered, then nothing
     *
     * @param city
     */
    public void removeCity(String city) {
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
        return null;
    }

}
