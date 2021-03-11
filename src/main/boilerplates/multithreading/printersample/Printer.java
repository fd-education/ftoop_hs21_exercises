package ch.ffhs.ftoop.multithreading.printersample;

class Printer implements Runnable {
	private String message;

	public Printer(String message) {
		this.message = message;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(i + ": " + message);
		}
	}

	// somewhere else
	public static void main(String[] args) {
		Runnable doSomething = new Printer("Running, running, running!");
		Runnable doSomethingElse = new Printer("Having some coffee!");
		Thread t1 = new Thread(doSomething);
		Thread t2 = new Thread(doSomethingElse);
		t1.start();
		t2.start();
	}

}