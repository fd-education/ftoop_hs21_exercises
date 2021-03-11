package ch.ffhs.ftoop.gcdemo;

//TODO Spielen mit den Heap Parametern
//TODO Spielen mit MAX

public class GcDemo {

	static int MAX = 1000000;
	static MyClass[] storage = new MyClass[MAX];

	public static void main(String[] args) throws InterruptedException {

		for (int i = 1; i <= MAX; i++) {
			System.out.println("Generating " + i + ", FreeMem is "
					+ Runtime.getRuntime().freeMemory() / 1024 / 1024);
			storage[i] = new MyClass(i);
			Thread.sleep(100);

		}
	}
}

/**
 * A class to demonstrate garbage collection and the finalize method.
 */
class MyClass {
	int x;
	byte[] bigArray;

	public MyClass(int i) {
		this.x = i;
		bigArray = new byte[1000000];
	}

	/**
	 * Called when object is recycled.
	 */
	@Override
	protected void finalize() {
		System.out.println("Finalizing " + x);
	}

}
