package ch.ffhs.ftoop.multithreading.divisor;

import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ble
 * 
 */

public class CalculateDivisorTest {

	@Test
	public void testCalculate() throws InterruptedException, ExecutionException, Exception {
		String text =  tapSystemOutNormalized(() -> CalculateDivisor.main(new String[] { "10", "10000", "4" }));

		assertEquals(
				"Ergebnis: Zahl mit maximaler Anzahl Divisoren: 7560 (64 Divisoren)\n",
				text);
	}

}
