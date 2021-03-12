// DONE !!!

package pva03.deadlock;
/**
 * Aufgabe: Dieses Programm demonstriert einen Deadlock. Lassen Sie dieses
 * Programm mehrfach laufen und schauen Sie, was passiert.
 * 
 * a) Erklären Sie das Verhalten des Programms. b) Korrigieren Sie das Programm,
 * so dass es sich korrekt verhält. Verändern Sie dabei nicht die Klasse Friend.
 * 
 * @author bele
 * 
 */
public class Deadlock {

	void doStuff() throws InterruptedException {
		final Friend alphonse = new Friend("Alphonse");
		final Friend gaston = new Friend("Gaston");
		final Object LOCK = new Object();

		//change: add synchronized
		synchronized (LOCK) {
			Thread gastonThread = new Thread(new Runnable() {
				public void run() {
					alphonse.bow(gaston);
				}
			}, "Gaston");
			gastonThread.start();

			gastonThread.join();
		}

		//change: add synchronized
		synchronized (LOCK) {
			Thread alphonseThread = new Thread(new Runnable() {
				public void run() {
					gaston.bow(alphonse);
				}
			}, "Alphonse");
			alphonseThread.start();

			alphonseThread.join();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Deadlock d = new Deadlock();
		d.doStuff();
	}
}

class Friend {
	private final String name;

	public Friend(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public synchronized void bow(Friend bower) {
		System.out.format("%s: %s" + "  has bowed to me!%n", this.name,
				bower.getName());
		bower.bowBack(this);
	}

	public synchronized void bowBack(Friend bower) {
		System.out.format("%s: %s" + " has bowed back to me!%n", this.name,
				bower.getName());
	}
}
