package pva09.datetime1;

import java.time.LocalDate;
import java.time.Period;

public class DateDifference {
    public static void printDifference(LocalDate date1, LocalDate date2) {
        Period difference = date1.until(date2);
        System.out.printf("There are %d years and %d months between %s and %s", difference.getYears(), difference.getMonths(), date1, date2);
    }
}