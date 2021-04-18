package pva05.divisor;

import java.util.concurrent.Callable;

public class DivisorCountCalculator implements Callable<Long> {

    private final long candidate;

    public DivisorCountCalculator(long candidate) {

        this.candidate = candidate;
    }

    @Override
    public Long call() throws Exception {
        long divisorCounter = 1;
        for (int i = 1; i <= (candidate + 1) / 2; i++) {
            if (candidate % i == 0) divisorCounter++;
        }
        return divisorCounter;
    }
}
