package pva09.datetime1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

public class DateFormatterTest {
    LocalDateTime dateTime;

    @BeforeEach
    void setup(){
        dateTime = LocalDateTime.of(2021, 5, 31, 14, 33);
    }

    @Test
    void formatDateTest(){
        String EXPECTED_OUTPUT = """
        Default format of LocalDate = 2021-05-31
        31::May::2021
        Default format of LocalDateTime = 2021-05-31T14:33
        31::May::2021 14::33::00
        Default format of Instant = 2021-05-31T12:33:00Z
        """;

        assertEquals(EXPECTED_OUTPUT, DateFormatter.formatDate(dateTime));
    }

}
