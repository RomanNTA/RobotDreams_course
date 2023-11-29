package cz.robodreams.javadeveloper.homeworks.hw03primitive;

import java.math.BigDecimal;

public class CalculateIncome {

    /**
     * Calculate daily expensive Note: one day has 8 working hours
     */
    public int dailyIncome(int hourlyRate) {
        int result = 0;
        // code your solution here

        result = hourlyRate * 8;

        // last statement will be assigment your temporary result to result variable
        //======
        return result;
    }

    /**
     * Calculate monthly expensive Note: one day has 8 working hours, one month has 20 working days
     */
    public int monthlyIncome(int hourlyRate) {
        int result = 0;
        // code your solution here

        result = hourlyRate * 8 * 20;

        // last statement will be assigment your temporary result to result variable
        //======
        return result;
    }

    public double averageExpensiveOnTeamMemberInDay(int testerHourRate, int programmerHourRate, int analystHourRate) {
        double result = 0;
        // code your solution here

        result = (testerHourRate + programmerHourRate + analystHourRate) / 3.0;

        // last statement will be assigment your temporary result to result variable
        //======

        return result;
    }

    /**
     * BONUS task
     */
    public BigDecimal averageExpensiveOnTeamMemberInDayRoundedToDecimalPlaces(int testerHourRate, int programmerHourRate, int analystHourRate,
                                                                              byte decimalPlace) {
        BigDecimal result = BigDecimal.ZERO;
        // code your solution here

        double dAverage = (testerHourRate + programmerHourRate + analystHourRate ) / 3.0;
        result = BigDecimal.valueOf(dAverage).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

        // last statement will be assigment your temporary result to result variable
        //======
        return result;
    }

    /**
     * Create report with monthExpensives in format: Analyst:$monthRate; Developer:$devMonthRate; Tester:$testerMonthRate
     * <p>
     * instead of $... give your numbers be aware of "; " between salary items. Contains ; and space
     */
    public String createReportWithMonthExpensive(int testerHourRate, int programmerHourRate, int analystHourRate) {
        String result = "";
        // code your solution here

        result = String.format("Analyst:%d; Developer:%d; Tester:%d",
                analystHourRate * 20 * 8,
                programmerHourRate * 20 * 8,
                testerHourRate * 20 * 8


        );

        // last statement will be assigment your temporary result to result variable
        //======
        return result;
    }

}
