package pva09.datetime2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;

public class DateManipulationTest {
    LocalDate date;

    @BeforeEach
    void setup(){
        date = LocalDate.of(2000, 7, 31);
    }

    @Test
    void print10DaysBeforeAndAfterTest() throws Exception{
        String EXPECTED_OUTPUT = """
                Ten days before was: 2000-07-21
                Actual date is: 2000-07-31
                Ten days after was: 2000-08-10
                """;

        String output = tapSystemOutNormalized(() -> DateManipulation.print10DaysBeforeAndAfter(date));

        assertEquals(EXPECTED_OUTPUT, output);
    }

    @Test
    void printDaysOfMonthTest() throws Exception{
        String EXPECTED_OUTPUT = "Juli in year 2000 has 31 days.\n";

        String output = tapSystemOutNormalized(() -> DateManipulation.printDaysOfMonth(date));
        assertEquals(EXPECTED_OUTPUT, output);
    }

    @Test
    void printPreviousWednesday_positive_simple() throws Exception {
        String output = tapSystemOutNormalized(() -> DateManipulation.printPreviousWednesday(date));

        assertEquals("The last wednesday before 2000-07-31 was 2000-07-26\n", output);
    }

    @Test
    void printDaysOfMonthNullTest() {
        assertThrows(NullPointerException.class, () -> DateManipulation.printDaysOfMonth(null));
    }

    @Test
    void print10DaysBeforeAndAfterNullTest() {
        assertThrows(NullPointerException.class, () -> DateManipulation.print10DaysBeforeAndAfter(null));
    }

    @Test
    void printPreviousWednesday_negative_nullThrows() {
        assertThrows(NullPointerException.class, () -> DateManipulation.printPreviousWednesday(null));
    }
}
