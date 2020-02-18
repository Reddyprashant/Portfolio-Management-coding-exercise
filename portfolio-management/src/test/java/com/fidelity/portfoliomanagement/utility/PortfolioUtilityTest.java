package com.fidelity.portfoliomanagement.utility;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PortfolioUtilityTest {


    @Test
    public void calculateAgeTest() {
        // Given date of birth is 8-31-1992
        // When calculateAge method is invoked
        int age = PortfolioUtility.calculateAge(LocalDate.of(1992, 8, 31));

        // Then the Age should be calculated as 27
        assertEquals(27, age, "Calculated age is same");
    }
}
