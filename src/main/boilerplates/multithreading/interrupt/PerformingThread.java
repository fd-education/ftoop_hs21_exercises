package ch.ffhs.ftoop.multithreading.interrupt;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

public final class PerformingThread {

    private long[] inputDataArray;

    private ArrayBlockingQueue<Long> outputDataQueue;

    public PerformingThread(long[] inputDataArray,
                            ArrayBlockingQueue<Long> outputDataQueue) {

        Objects.requireNonNull(inputDataArray);
        Objects.requireNonNull(outputDataQueue);
        this.inputDataArray = inputDataArray;
        this.outputDataQueue = outputDataQueue;
    }

    public void workDataArray() {

        /*
         * Fügen Sie INNERHALB DIESER METHODE (VERTEILT) Ihren Code ein.
         * Entfernen oder verändern Sie keine existierenden Codezeilen.
         */

        for (int i = 0; i < inputDataArray.length; i++) {

            long result = performLongCalculation(inputDataArray[i]);

            try {

                outputDataQueue.put(Long.valueOf(result));

            } catch (InterruptedException e) {

            }
        }
    }

    private long performLongCalculation(long value) {

        /* [...] Implementation omitted. */
        return 0;
    }

    private void writeResultToDatabase(long result) {

        /* [...] Implementation omitted. */
    }
}