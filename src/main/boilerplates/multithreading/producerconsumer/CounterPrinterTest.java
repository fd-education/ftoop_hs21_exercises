package ch.ffhs.ftoop.multithreading.producerconsumer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

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

	// TODO Fuegen Sie einen eigenen Test ein.
}
