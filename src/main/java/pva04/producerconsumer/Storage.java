package pva04.producerconsumer;

//import java.util.ArrayList;
//import java.util.List;

/**
 * Simple storage implementation that stores and provides a single integer value
 * and provides a monitoring method.
 */
public class Storage implements StorageIf {

	private int value;
	private boolean hasValue = false;

	/**
	 * Get the current value in the storage; set hasValue to false,
	 * as the value is known after this operation and should not be gotten twice
	 * @return current value
	 * @throws InterruptedException if current thread is interrupted
	 */
	@Override
	public int getValue() throws InterruptedException {
		this.hasValue = false;
		return value;
	}

	/**
	 * Set the current value in the storage; set hasValue to true,
	 * as the value is new after this operation and can be gotten once
	 * @throws InterruptedException if current thread is interrupted
	 */
	@Override
	public void setValue(int value) throws InterruptedException {
		this.value = value;
		this.hasValue = true;
	}

	/**
	 * @return TRUE if storage contains a new value, false if storage contains no or an old value
	 */
	@Override
	public boolean hasValue(){
		return hasValue;
	}
}
