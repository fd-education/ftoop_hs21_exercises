package ch.ffhs.ftoop.multithreading.producerconsumer;

public interface StorageIf {

	/**
	 * Gibt den aktuellen Wert zurück.
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public int getValue() throws InterruptedException;

	/**
	 * Setzt einen neuen aktuellen Wert.
	 * 
	 * @param value
	 * @throws InterruptedException
	 */
	public void setValue(int value) throws InterruptedException;

	/**
	 * Gibt true zurück, wenn es einen neuen, noch nicht konsumierten Wert im
	 * Objekt hat.
	 * 
	 * @return
	 */
	public boolean hasValue();

}
