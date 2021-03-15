package pva04.producerconsumer;

public interface StorageIf {

	/**
	 * Gibt den aktuellen Wert zurück.
	 * 
	 * @return current value in the storage
	 * @throws InterruptedException if thread is interrupted
	 */
	int getValue() throws InterruptedException;

	/**
	 * Setzt einen neuen aktuellen Wert.
	 * 
	 * @param value value to set in the storage
	 * @throws InterruptedException if thread is interrupted
	 */
	void setValue(int value) throws InterruptedException;

	/**
	 * Gibt true zurück, wenn es einen neuen, noch nicht konsumierten Wert im
	 * Objekt hat.
	 * 
	 * @return TRUE if storage contains a new value, FALSE else
	 */
	boolean hasValue();

}
