package com.library.util;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public final class FineCalculator {
    private FineCalculator(){}
    public static double calculate(LocalDate dueDate,LocalDate returnDate,double dailyRate){if(returnDate==null||!returnDate.isAfter(dueDate))return 0.0;long daysLate=ChronoUnit.DAYS.between(dueDate,returnDate);return daysLate*dailyRate;}
}