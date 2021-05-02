package pva05.forkjoin;

import lombok.Getter;

@Getter
public class Result {
    private final int firstOccurrenceIndex;
    private final int lastOccurrenceIndex;
    private final int longestSequence;
    private final int value;

    public Result(final int firstOccurrenceIndex, final int lastOccurrenceIndex, final int longestSequence, final int value) throws IllegalArgumentException {

        if(!isValid(firstOccurrenceIndex, lastOccurrenceIndex, longestSequence)){
            //throw new IllegalArgumentException("The longest sequence must match the difference of the first and last occurrence.");
        }

        this.firstOccurrenceIndex = firstOccurrenceIndex;
        this.lastOccurrenceIndex = lastOccurrenceIndex;
        this.value = value;
        this.longestSequence = longestSequence;
    }

    private boolean isValid(int firstOccIndex, int lastOccIndex, int providedLongestSeq){
        int expectedLongestSeq = 1 + lastOccIndex - firstOccIndex;

        return expectedLongestSeq == providedLongestSeq;
    }

    @Override
    public String toString(){
        return ("\n" + value + " --> First Occurrence: " + firstOccurrenceIndex + ", last occurrence: " + lastOccurrenceIndex + ", longest sequence: " + longestSequence );
    }
}
