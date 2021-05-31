package pva09.datetime1;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.format.DateTimeFormatter.ofPattern;

// LocalDateTime instantFromDate = LocalDateTime.ofInstant()

public class DateFormatter {

    /**
     * Method parses a LocalDateTime objects to special output formats
     * @param dateTime current date and time
     * @return formatted String
     */
    public static String formatDate(LocalDateTime dateTime){

        // Formatters for date and dateTime
        DateTimeFormatter dateColonSeparated = ofPattern("dd::MMM::yyyy", Locale.US);
        DateTimeFormatter dateTimeColonSeparated = ofPattern("dd::MMM::yyyy HH::mm::ss", Locale.US);

        // Parse dateTime to date
        LocalDate date = dateTime.toLocalDate();

        // Parse dateTime to instant
        Instant dateTimeInstant = dateTime.atZone(ZoneId.systemDefault()).toInstant();

        // return formatted String
        return String.format("""
                        Default format of LocalDate = %s
                        %s
                        Default format of LocalDateTime = %s
                        %s
                        Default format of Instant = %s
                        """,
               date, dateColonSeparated.format(date), dateTime, dateTimeColonSeparated.format(dateTime), dateTimeInstant);
    }

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.of(2016, 9, 16, 11, 46, 1, 455);
        LocalDateTime dateTime2 = LocalDateTime.of(2021, 5, 31, 14, 33);
        System.out.println(DateFormatter.formatDate(dateTime2));
    }
}
