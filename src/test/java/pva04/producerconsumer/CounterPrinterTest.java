package pva04.producerconsumer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CounterPrinterTest {

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Test
	public void testZaehlerDrucker() throws InterruptedException {
		CounterPrinter.main(new String[] { "1", "25" });

		assertEquals(
				"1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 ",
				systemOutRule.getLog());
	}

	@Test
	public void testZaehlerDruckerNegativStart() throws InterruptedException {
		CounterPrinter.main(new String[] { "-1", "25" });

		assertEquals(
				"-1 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 ",
				systemOutRule.getLog());
	}

	@Test
	public void testZaehlerDruckerNegativEnd() throws InterruptedException {
		CounterPrinter.main(new String[] { "-20", "-5" });

		assertEquals(
				"-20 -19 -18 -17 -16 -15 -14 -13 -12 -11 -10 -9 -8 -7 -6 -5 ",
				systemOutRule.getLog());
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
