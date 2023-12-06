package cz.robodreams.javadeveloper.homeworks.hw04ifbit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//@Disabled
class BMICalculatorTest {

    BMICalculator bmiCalculator = new BMICalculator();

    @ParameterizedTest
    @CsvSource({
            "45,180,Velmi vážná podvýživa",
            "50,180,Vážná podvýživa",
            "58,180,Podvýživa",
            "80,180,Normální",
            "90,180,Nadváha",
            "100,180,Nadváha I. stupně (Střední obezita)",
            "120,180,Nadváha II. stupně (Vážná obezita)",
            "130,180,Nadváha III. stupně (Velmi vážná obezita) "
    })
    void calculate(int weight,int height, String expected) {
        assertThat(bmiCalculator.calculate(weight, height))
                .isEqualToIgnoringWhitespace(expected);
    }
}
