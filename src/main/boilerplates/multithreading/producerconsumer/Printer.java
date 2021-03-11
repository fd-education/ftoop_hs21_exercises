package ch.ffhs.ftoop.multithreading.producerconsumer;

public class Printer extends Thread {
	private Storage storage;

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
		while (true) {
			try {
				System.out.print(storage.getValue() + " ");
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
