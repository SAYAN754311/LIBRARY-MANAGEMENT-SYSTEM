package com.library;
import com.library.util.FineCalculator;
import java.time.LocalDate;
public class FineCalculatorTest {
    public static void main(String[] args){check("on-time",FineCalculator.calculate(LocalDate.of(2026,1,10),LocalDate.of(2026,1,10),5)==0);check("late",FineCalculator.calculate(LocalDate.of(2026,1,10),LocalDate.of(2026,1,13),5)==15);check("early",FineCalculator.calculate(LocalDate.of(2026,1,10),LocalDate.of(2026,1,8),5)==0);System.out.println("All tests passed.");}
    private static void check(String name,boolean condition){if(!condition)throw new AssertionError("FAIL: "+name);System.out.println("PASS: "+name);}
}