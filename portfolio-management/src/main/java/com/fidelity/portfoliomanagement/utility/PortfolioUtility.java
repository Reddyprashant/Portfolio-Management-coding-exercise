package com.fidelity.portfoliomanagement.utility;

import java.time.LocalDate;
import java.time.Period;

public class PortfolioUtility {


    public static int calculateAge(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate,currentDate).getYears();
    }
}
