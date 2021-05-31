package pva09.datetime2;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;

public class DateManipulation {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2000, 7, 31);

        printDaysOfMonth(date);
        print10DaysBeforeAndAfter(date);
        printPreviousWednesday(date);
    }

    public static void print10DaysBeforeAndAfter(LocalDate date) {
        Objects.requireNonNull(date);

        LocalDate before = date.minusDays(10);
        LocalDate after = date.plusDays(10);

        System.out.printf("""
                Ten days before was: %s
                Actual date is: %s
                Ten days after was: %s
                """, before, date, after);
    }

    public static void printDaysOfMonth(LocalDate date){
        Objects.requireNonNull(date);

        // Get number of days in the respective month
        int daysOfMonth = date.getMonth().length(date.isLeapYear());

        // Print a formatted String (https://www.baeldung.com/java-printstream-printf#date_format)
        System.out.printf("%1$tB in year %1$tY has %2$s days.\n", date, daysOfMonth);
    }

    public static void printPreviousWednesday(LocalDate date){
        Objects.requireNonNull(date);
        LocalDate previousWednesday = date.with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY));

        System.out.printf("The last wednesday before %s was %s\n", date, previousWednesday);
    }
}