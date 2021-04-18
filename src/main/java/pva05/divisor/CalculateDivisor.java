package pva05.divisor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.*;

/**
 * Das folgende Programm soll aus einem vorgegebene Interval von Long-Zahlen die
 * Zahl zurückgeben, die die meisten Divisoren hat. Sollte es mehrere solche
 * Zahlen geben, so soll die kleinste dieser Zahlen ausgegeben werden.
 * 
 * Die Berechnung soll in n Threads stattfinden, die via Executor Framework
 * gesteuert werden, und sich das Problem aufteilen - jeder Thread soll eine
 * Teilmenge des Problems lösen. Verwenden Sie bitte einen FixedThreadPool und
 * implementieren Sie die Worker als Callable.
 * 
 * @author ble
 * 
 */
public class CalculateDivisor {

	long von, bis;
	int threadCount;

	/**
	 * @param von
	 *            untere Intervallgrenze
	 * @param bis
	 *            obere Intervallgrenze
	 * @param threadCount
	 *            Abzahl der Threads, auf die das Problem aufgeteilt werden soll
	 */
	public CalculateDivisor(long von, long bis, int threadCount) {
		this.von = von;
		this.bis = bis;
		this.threadCount = threadCount;

	}

	/**
	 * Berechnungsroutine
	 * 
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	DivisorResult calculate() throws InterruptedException, ExecutionException {
		final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

		final HashMap<Long, Future<Long>> futureMap = new HashMap<>();

		for (long i = von; i <= bis; i++) {
			final Future<Long> future = executorService.submit(new DivisorCountCalculator(i));
			futureMap.put(i, future);
		}

		long maxDivisorCountValue = Long.MAX_VALUE;
		long maxDivisorCount = Long.MIN_VALUE;

		for (Entry<Long, Future<Long>> entry : futureMap.entrySet()) {
			Long index = entry.getKey();
			Future<Long> future = entry.getValue();
			long divisorCount = future.get();

			if (divisorCount >= maxDivisorCount) {
				if (!(divisorCount == maxDivisorCount && index > maxDivisorCountValue)) {
					maxDivisorCountValue = index;
				}
				maxDivisorCount = divisorCount;
			}
		}

		return new DivisorResult(maxDivisorCountValue, maxDivisorCount);
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		if (args.length != 3) {
			System.out
					.println("Usage: CalculateDivisor <intervalStart> <intervalEnd> <threadCount>");
			System.exit(1);
		}
		long von = Long.parseLong(args[0]);
		long bis = Long.parseLong(args[1]);
		int threads = Integer.parseInt(args[2]);

		CalculateDivisor cp = new CalculateDivisor(von, bis, threads);
		System.out.println("Ergebnis: " + cp.calculate());
	}

}



