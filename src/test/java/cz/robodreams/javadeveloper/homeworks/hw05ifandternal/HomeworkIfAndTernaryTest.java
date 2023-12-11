package cz.robodreams.javadeveloper.homeworks.hw05ifandternal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HomeworkIfAndTernaryTest {

    HomeworkIfAndTernary homeworkIfAndTernary = new HomeworkIfAndTernary();

    @ParameterizedTest
    @CsvSource({
            "-4,0,1,Neznama operace",
            "4,-4,1,Neznama operace",
            "-4,-4,1,Neznama operace",
            "4,3,8,Neznama operace",
            "4,3,-1,Neznama operace",
            "4,3,0,Neznama operace",
            "0,0,1,Neznama operace",
            "0,0,2,Neznama operace",

            "4,2,1,Area: 8; Perimeter 12",
            "5,0,2,Area: 78; Perimeter 31",

    })
    void calculateAreaAndPerimeter(int dimX, int dimY, int operation, String expected) {
        assertThat(homeworkIfAndTernary.calculateAreaAndPerimeter(dimX, dimY, operation))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "2000,je",
            "2004,je",
            "2020,je",
            "1900,neni; zbyva 4",
            "2001,neni; zbyva 3",
            "2100,neni; zbyva 4",
            "2023,neni; zbyva 1",
            "2022,neni; zbyva 2",
    })
    void isYearALeapYear(int year, String expected) {
        assertThat(homeworkIfAndTernary.isYearALeapYear(year))
                .isEqualTo(expected);
    }

    @Test
    @Disabled("kdo chce necht si toto smaze, cely radek ")
    void allYearsTest() {
        for (int i = 0; i < 5100; i++) {
            assertThat(homeworkIfAndTernary.isYearALeapYear(i).equals("je"))
                    .as("Trying leap years for " + i)
                    .isEqualTo(LocalDate.of(i, 1, 1).isLeapYear());
        }

    }
}
