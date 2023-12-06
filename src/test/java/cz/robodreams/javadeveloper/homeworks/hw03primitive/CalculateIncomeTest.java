package cz.robodreams.javadeveloper.homeworks.hw03primitive;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
@Disabled
class CalculateIncomeTest {

    CalculateIncome calculateIncome = new CalculateIncome();

    @ParameterizedTest
    @CsvSource({
            "500,4000",
            "200,1600",
            "-200,-1600"
    })
    void dailyIncome(int hourlyRate, int expectedResult) {

        assertThat(calculateIncome.dailyIncome(hourlyRate))
                .isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "500,80000",
            "200,32000",
            "-200,-32000"
    })
    void monthlyIncome(int hourlyRate, int expectedResult) {

        assertThat(calculateIncome.monthlyIncome(hourlyRate))
                .isEqualTo(expectedResult);


        assertThat(calculateIncome.monthlyIncome(hourlyRate))
                .isEqualTo(expectedResult);

    }

    @ParameterizedTest
    @CsvSource({
            "500,500,500,500.0",
            "400,300,200,300",
            "500,523,332,451.6666666666667"
    })
    void averageExpensiveOnTeamMemberInDay(int testerHourRate, int programmerHourRate, int analystHourRate, double expected) {

        assertThat(calculateIncome.averageExpensiveOnTeamMemberInDay(testerHourRate, programmerHourRate, analystHourRate))
                .isEqualTo(expected);

    }

    @ParameterizedTest
    @CsvSource({
            "500,523,332,451.67",
            "200,215,246,220,33"
    })
    void averageExpensiveOnTeamMemberInDay_decimalPart(int testerHourRate, int programmerHourRate, int analystHourRate, double expected) {

        assertThat(calculateIncome.averageExpensiveOnTeamMemberInDay(testerHourRate, programmerHourRate, analystHourRate))
                .isCloseTo(expected, Percentage.withPercentage(1.0));

    }

    @ParameterizedTest
    @CsvSource({
            "500,523,332,2,451.67",
            "500,523,332,0,452",
            "200,215,246,4,220.3333"
    })
    void averageExpensiveOnTeamMemberInDayRoundedToDecimalPlaces(int testerHourRate, int programmerHourRate, int analystHourRate, byte decimalPlace,
            BigDecimal expected) {

        BigDecimal result = calculateIncome.averageExpensiveOnTeamMemberInDayRoundedToDecimalPlaces(testerHourRate, programmerHourRate,
                analystHourRate, decimalPlace);

        assertThat(result)
                .isEqualTo(expected);
    }

    @Test
    void createReportWithMonthExpensive() {

        String reportWithMonthExpensive = calculateIncome.createReportWithMonthExpensive(300, 500, 400);

        assertThat(reportWithMonthExpensive)
                .isEqualTo("Analyst:64000; Developer:80000; Tester:48000");
    }

}
