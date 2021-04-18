package pva05.divisor;


import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ble
 */

public class CalculateDivisorTest {

    @Test
    public void testCalculate() throws Exception {
        final long start = System.currentTimeMillis();
//        assertEquals(
//                "Ergebnis: Zahl mit maximaler Anzahl Divisoren: 7560 (64 Divisoren)\n",
//                tapSystemOutNormalized(() -> CalculateDivisor.main(new String[]{"10", "10000", "4"})));


        assertEquals(
                "Ergebnis: Zahl mit maximaler Anzahl Divisoren: 720720 (240 Divisoren)\n",
                tapSystemOutNormalized(() -> CalculateDivisor.main(new String[]{"10", "1000000", "12"})));
        System.out.println(System.currentTimeMillis() - start);

    }

}
