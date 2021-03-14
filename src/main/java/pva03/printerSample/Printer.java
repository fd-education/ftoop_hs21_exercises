package pva03.printerSample;

class Printer implements Runnable {
	private String message;
	private static Object LOCK = new Object();

	public Printer(String message) {
		this.message = message;
	}

	public void run() {
		synchronized(LOCK) {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					for (int i = 0; i < 100; i++) {
						System.out.println(i + ": " + message);

						LOCK.notify();
						LOCK.wait();
					}
					Thread.currentThread().interrupt();
				} catch (InterruptedException iex) {
					Thread.currentThread().interrupt();
				}
			}
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