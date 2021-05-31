package pva09.datetime1;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
class DateDifferenceTest {
    @Test
    void printDifference_positive_simple() throws Exception {
        LocalDate date1 = LocalDate.of(2002, 10, 20);
        LocalDate date2 = LocalDate.of(2004, 10, 1);
        String output = tapSystemOutNormalized(() ->DateDifference.printDifference(date1, date2));

        assertEquals("There are 1 years and 11 months between 2002-10-20 and 2004-10-01", output);
    }
}