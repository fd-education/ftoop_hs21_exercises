package ch.ffhs.ftoop.multithreading.producerconsumer;

/**
 * 
 * Der Aufruf ben�tigt zwei Parameter min und max - der Zaehler beginnt bei min
 * zu zaehlen und terminiert bei max.
 * 
 */

public class CounterPrinter {

	public static void main(String[] args) throws InterruptedException {
		if (args.length != 2) {
			System.out.println("Usage: CounterPrinter <min> <max>");
			System.exit(1);
		}

		Storage s = null; // new Storage();
		Printer d = new Printer(s);
		Counter z = new Counter(s, Integer.parseInt(args[0]),
				Integer.parseInt(args[1]));

		z.start();
		d.start();

		// bissi warten, damit der Test funktioniert
		Thread.sleep(5000);

	}

}
