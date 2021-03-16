package pva04.producerconsumer;

public class Printer extends Thread {
	private final Storage storage;

	Printer(Storage s) {
		this.storage = s;
	}

	/**
	 * Holt einen Wert vom Zaehler und gibt ihn aus, gefolgt von einem einzelnen
	 * Leerzeichen.
	 *
	 */
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {

			// use the storage object as a sync lock
			synchronized(storage) {
				try {
					while(storage.hasValue()) {
						System.out.print(storage.getValue() + " ");
						Thread.sleep(100);

						// notify the monitor about the terminated action and wait
						storage.notify();
						storage.wait();
					}

					// interrupt thread if CPU time is awarded, but no value in storage
					// as there might occur sync problems in this case
					Thread.currentThread().interrupt();

				} catch (InterruptedException e) {

					// interrupt within catch block, bc Exception gets swallowed otherwise
					Thread.currentThread().interrupt();

					e.printStackTrace();
				}
			}
		}

	}

}
