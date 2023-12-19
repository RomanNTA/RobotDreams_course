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

        if (operation == 1 && dimensionX > 0 && dimensionY > 0) {

            long lArea = dimensionX * dimensionY;
            long lPerimeter = 2* (dimensionX + dimensionY);
            return String.format("Area: %d; Perimeter %d", lArea, lPerimeter);

        } else if (operation == 2  && dimensionX > 0) {

            // long lArea = Math.round(Math.PI * dimensionX * dimensionX);
            long lArea = (long) (Math.PI * dimensionX * dimensionX);
            long lPerimeter = Math.round(2 * Math.PI * dimensionX);
            return String.format("Area: %d; Perimeter %d", lArea, lPerimeter);

        } else {
            return "Neznama operace";
        }
    }

    /**
     * Return "je" if year is a leap year, otherwise "neni; zbyva {years}",
     * <p>
     * Write your solution as single ternary operator
     *
     * @param year year
     * @return "je" if year is a leap year, otherwise "neni; zbyva {years}",
     */
    public String isYearALeapYear(int year) {

        return (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))
                ? "je"
                : "neni; zbyva " + (4 - year % 4);

    }

}