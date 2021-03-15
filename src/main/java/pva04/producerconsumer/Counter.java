package pva04.producerconsumer;

public class Counter extends Thread {

	private final Storage storage;
	private int max, min;

	/**
	 * 
	 * @param s
	 *            Das Speicherobject, das die aktuelle Zahl haelt.
	 * @param min
	 *            Der Startwert für den Zaehler
	 * @param max
	 *            Der Endwert für den Zaehler (einschliesslich)
	 * 
	 */
	Counter(Storage s, int min, int max) {
		this.storage = s;
		this.max = max;
		this.min = min;
	}

	/**
	 * Diese Run Methode zählt den Wert in Speicher hoch - von min bis max
	 * (einschliesslich).
	 * 
	 */
	@Override
	public void run() {

		// use storage object as sync lock
		synchronized(storage) {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					while (min <= max) {

						// post increment: set value, THEN increment
						storage.setValue(min++);

						//notify monitor about finished action and wait
						storage.notify();
						storage.wait();
					}

					// interrupt thread if max is reached to stop the counter
					Thread.currentThread().interrupt();
				} catch (InterruptedException iex) {

					// reset interrupt, bc the catch block swallows it otherwise
					Thread.currentThread().interrupt();
				}
			}
		}
	}

}
