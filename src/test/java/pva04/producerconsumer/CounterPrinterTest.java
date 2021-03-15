package pva04.producerconsumer;

import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.*;

import static org.junit.jupiter.api.Assertions.*;

public class CounterPrinterTest {

	@Test()
	public void testZaehlerDrucker() throws Exception {
		String text =  tapSystemOutNormalized(() -> CounterPrinter.main(new String[]{"1", "25"}));

		assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 ", text);
	}

	@Test()
	public void testZaehlerDruckerNegativStart() throws Exception {
		String text =  tapSystemOutNormalized(() -> CounterPrinter.main(new String[] { "-1", "25" }));

		assertEquals("-1 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 ", text);
	}

	@Test()
	public void testZaehlerDruckerNegativEnd() throws Exception {
		String text =  tapSystemOutNormalized(() -> CounterPrinter.main(new String[] { "-20", "-5" }));

		assertEquals("-20 -19 -18 -17 -16 -15 -14 -13 -12 -11 -10 -9 -8 -7 -6 -5 ", text);
	}

	@Test
	public void setAndHasValueTest() throws InterruptedException{
		Storage s = new Storage();

		s.setValue(100);
		assertTrue(s.hasValue());

		assertEquals(100, s.getValue());
		assertFalse(s.hasValue());
	}
}
