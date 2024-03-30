package cz.robodreams.javadeveloper.project.control.client;

import cz.robodreams.javadeveloper.project.control.common.Const;
import lombok.Getter;

@Getter
public class TestedRange {

    private Integer intFrom;
    private Integer intTo;
    private Integer maxRange;
    private Integer out;
    private String outputString;
    private String errorMessage;

    private final String errMessage1 = "Výběr není v požadovaném rozsahu";
    private final String errMessage2 = "Chybný převod vstupního zadání";

    public Boolean test(String input, Integer maxRange) {

        this.maxRange = maxRange;
        this.outputString = "";

        String[] out = input.split("-");

        if (out.length == 2) {
            try {

                int intFrom = Integer.parseInt(out[0]);
                int intTo = Integer.parseInt(out[1]);

                if (!Const.testIndex.test(intFrom, maxRange) && !Const.testIndex.test(intTo, maxRange)) {

                errorMessage = errMessage1;
                    return false;
                }

                for (int i = Math.min(intFrom, intTo); i <= Math.max(intFrom, intTo); i++) {
                    this.outputString += i + ";";
                }
                return true;

            } catch (NumberFormatException e) {
                errorMessage = errMessage1;
                return false;
            }

        } else {
            errorMessage = errMessage2;
            return false;
        }
    }

}
