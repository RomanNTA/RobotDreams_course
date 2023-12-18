package cz.robodreams.javadeveloper.homeworks.hw09collections.geo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GeoCityTest {


    private final static String PRAGUE = "praha";
    private final static String BRNO = "brno";
    private final static String PLZEN = "plzen";
    private final static String DOMAZLICE = "domazlice";
    private final static String MLADA_BOLESLAV = "mlada bloleslav";
    private final static String CESKA_KAMENICE = "ceska kamenice";
    private final static String CESKA_LIPA = "ceska lipa";
    private final static String KLATOVY = "klatovy";
    private final static String OSTRAVA = "ostrava";
    private final static String OPAVA = "opava";

    GeoCity geoCity;

    @BeforeEach
    void setUp() {
        geoCity = new GeoCity();
    }

    @Test
    void registerCity() {
        geoCity.registerCity(PRAGUE);
        assertThat(geoCity.cityMap)
                .hasSize(1)
                .containsKey(PRAGUE);

        geoCity.registerCity(PRAGUE);
        assertThat(geoCity.cityMap)
                .hasSize(1)
                .containsKey(PRAGUE);

    }

    @Test
    void connectCity() {
        geoCity.registerCity(PRAGUE);
        geoCity.registerCity(BRNO);

        geoCity.connectCity(PRAGUE, BRNO);

        assertThat(geoCity.cityMap)
                .containsKeys(PRAGUE, BRNO);
        assertThat(geoCity.cityMap.get(PRAGUE)).contains(BRNO);
        assertThat(geoCity.cityMap.get(BRNO)).contains(PRAGUE);



    }

    @Test
    void getCityConnections() {
        geoCity.registerCity(PRAGUE);
        geoCity.registerCity(BRNO);
        geoCity.registerCity(PLZEN);

        geoCity.connectCity(PRAGUE, BRNO);
        geoCity.connectCity(PRAGUE, PLZEN);

        assertThat(geoCity.getCityConnections(PRAGUE))
                .contains(BRNO, PLZEN);
    }

    @Test
    void removeConnections() {
        geoCity.registerCity(PRAGUE);
        geoCity.registerCity(BRNO);
        geoCity.registerCity(PLZEN);
        geoCity.registerCity(DOMAZLICE);

        geoCity.connectCity(PRAGUE, BRNO);
        geoCity.connectCity(PRAGUE, PLZEN);
        geoCity.connectCity(PLZEN, DOMAZLICE);

        geoCity.removeConnections(PRAGUE, PLZEN);

        assertThat(geoCity.getCityConnections(PRAGUE))
                .contains(BRNO);
        assertThat(geoCity.getCityConnections(PLZEN)).contains(DOMAZLICE);
    }

    @Test
    void removeCity() {
        geoCity.registerCity(PRAGUE);
        geoCity.registerCity(BRNO);
        geoCity.registerCity(PLZEN);
        geoCity.registerCity(DOMAZLICE);

        geoCity.connectCity(PRAGUE, BRNO);
        geoCity.connectCity(PRAGUE, PLZEN);
        geoCity.connectCity(PLZEN, DOMAZLICE);

        geoCity.removeCity(PLZEN);

        assertThat(geoCity.getCityConnections(PRAGUE))
                .contains(BRNO);
        assertThat(geoCity.getCityConnections(DOMAZLICE))
                .isEmpty();

    }

    @Test
    void getAllPossibleDestinationFromCity_empty() {
        geoCity.registerCity(PRAGUE);
        assertThat(geoCity.getAllPossibleDestinationFromCity(PRAGUE)).isEmpty();
    }

    @Test
    void getAllPossibleDestinationFromCity() {
        geoCity.registerCity(PRAGUE);
        geoCity.registerCity(BRNO);
        geoCity.registerCity(PLZEN);
        geoCity.registerCity(DOMAZLICE);
        geoCity.registerCity(MLADA_BOLESLAV);
        geoCity.registerCity(CESKA_KAMENICE);
        geoCity.registerCity(CESKA_LIPA);
        geoCity.registerCity(KLATOVY);
        geoCity.registerCity(OSTRAVA);
        geoCity.registerCity(OPAVA);

        geoCity.connectCity(PRAGUE, BRNO);
        geoCity.connectCity(PRAGUE, PLZEN);
        geoCity.connectCity(PRAGUE, MLADA_BOLESLAV);

        geoCity.connectCity(MLADA_BOLESLAV, CESKA_LIPA);
        geoCity.connectCity(CESKA_LIPA, CESKA_KAMENICE);

        geoCity.connectCity(PLZEN, DOMAZLICE);
        geoCity.connectCity(PLZEN, KLATOVY);

        geoCity.connectCity(OSTRAVA, OPAVA);

        assertThat(geoCity.getAllPossibleDestinationFromCity(PRAGUE))
                .contains(
                        PRAGUE,
                        BRNO,
                        PLZEN,
                        DOMAZLICE,
                        MLADA_BOLESLAV,
                        CESKA_KAMENICE,
                        CESKA_LIPA,
                        KLATOVY
                );
    }
}
