package cz.robodreams.javadeveloper.homeworks.hw05ifandternal;

public class HomeworkIfAndTernary {

    /**
     * @param dimensionX dimension x, for circle is radius
     * @param dimensionY dimension y, for circle 0 (ignore value)
     * @param operation  operation 1-> rectangle, 2 -> circle
     * @return calculated area and perimeter in format "Area: %d; Perimeter %d" round to whole number, for unknown operation or invalid entry  return
     * "Neznama operace"
     */
    public String calculateAreaAndPerimeter(int dimensionX, int dimensionY, int operation) {
        return "";
    }

    /**
     * Return "je" if year is a leap year, otherwise "neni; zbyva {years}",
     * <p>
     * Write your solution as single ternary operator
     *
     *
     *
     *      rok není dělitelný 4 → běžný rok, 365 dní, (např. 2023),
     *      rok je dělitelný 4, zároveň to ale není přelom století (na konci není 00) → rok je přestupný, 366 dní, (např, 2020 nebo 2024),
     *      rok je přelomem století, je dělitelný 100, ale zároveň první dvojčíslí není dělitelné 4 → rok není přestupný, 365 dní, (např. 1900 nebo 2100),
     *      rok je dělitelný 4 i 100 i 400 → rok je přestupný (např. 2000).
     *
     *      Přestupný rok bude v těchto následujících letech: 2024, 2028, 2032, 2036, 2040, 2044, 2048, 2052, 2056, 2060, 2064, 2068, 2072, 2076, 2080, 2084, 2088, 2092, 2096, 2104.
     *      * @param year
     *      * @return
     *
     *
     * @param year year
     * @return "je" if year is a leap year, otherwise "neni; zbyva {years}",
     */
    public String isYearALeapYear(int year) {
        return "";
    }

}
