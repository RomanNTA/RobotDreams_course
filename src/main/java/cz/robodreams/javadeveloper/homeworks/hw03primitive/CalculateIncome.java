package cz.robodreams.javadeveloper.homeworks.hw03primitive;

import java.math.BigDecimal;

public class CalculateIncome {

    /**
     * Calculate daily expensive Note: one day has 8 working hours
     * hourlyRate ak je hodinova sadzba, za 8 hodín potom bude výsledok
     */
    public int dailyIncome(int hourlyRate) {
        int result = 0;
        // code your solution here
        // last statement will be assigment your temporary result to result variable
         result = 8 * hourlyRate;
        //======
        return result;
    }

    /**
     * Calculate monthly expensive Note: one day has 8 working hours, one month has 20 working days
     */
    public int monthlyIncome(int hourlyRate) {
        int result = 0;
        // code your solution here
        // last statement will be assigment your temporary result to result variable
        //======
        result = 8 * 20 * hourlyRate;
        return result;
    }

    public double averageExpensiveOnTeamMemberInDay(int testerHourRate, int programmerHourRate, int analystHourRate) {
        double result = 0;
        // code your solution here
        // last statement will be assigment your temporary result to result variable
        //======
        result = (testerHourRate + programmerHourRate + analystHourRate)/3;
        return result;
    }

    /**
     * BONUS task
     */
    public BigDecimal averageExpensiveOnTeamMemberInDayRoundedToDecimalPlaces(int testerHourRate, int programmerHourRate, int analystHourRate,
            byte decimalPlace) {
        BigDecimal result = BigDecimal.ZERO;
        // code your solution here
        // last statement will be assigment your temporary result to result variable
        //======
        result = new BigDecimal((testerHourRate + programmerHourRate + analystHourRate) * 8 / 3);
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
        // last statement will be assigment your temporary result to result variable
        //======
        result = String.format("Tester has: %d Programmer has: %d Analyst has: %d", testerHourRate, programmerHourRate, analystHourRate);
        return result;
    }

}
