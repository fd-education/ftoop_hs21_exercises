package pva05.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SequenceEvaluator extends RecursiveTask<List<Result>> {
    private final List<Integer> arr;
    private final List<Result> results = new ArrayList<>();
    private static final int THRESHOLD = 1;

    public SequenceEvaluator(final List<Integer> values) throws IllegalArgumentException{
        //if(values.size() == 0) throw new IllegalArgumentException("The array to analyse must contain at least one value.");
        this.arr = values;
    }

    @Override
    protected List<Result> compute(){
        if(arr.size() == THRESHOLD){
            System.out.println(arr);
            results.add(new Result(1, 1, 1, arr.get(0)));
            return results;
        }

        int midPos = findMidPos(arr);
        if(midPos == -1){
            System.out.println(arr);
            results.add(new Result(-1, -1, arr.size(), arr.get(0)));
            return results;
        }

        final List<Integer> lower = arr.subList(0, midPos + 1);
        final List<Integer> upper = arr.subList(midPos + 1, arr.size());
        System.out.println(lower);
        System.out.println(upper);
        final SequenceEvaluator seqEvLower = new SequenceEvaluator(lower);
        final SequenceEvaluator seqEvUpper = new SequenceEvaluator(upper);

        invokeAll(seqEvLower, seqEvUpper);

        results.addAll(seqEvLower.join());
        results.addAll(seqEvUpper.join());
        return results;
    }

    private int findMidPos(final List<Integer> arr){
        if(arr.size() <= 1) return -1;
        boolean found = false;
        int midPos = (arr.size() / 2) - 1;

        for (int i = midPos; i < arr.size()-1; i++) {
               if (arr.get(i) != arr.get(i + 1)) {
                   found = true;
                   midPos = i;
                   break;
            }
        }

        if(!found){
           for(int i = midPos; i>=1; i--) {
               if (arr.get(i - 1) != arr.get(i)) {
                   found = true;
                   midPos = i - 1;
                   break;
               }
           }
        }

        return found? midPos : -1;
    }


    public static void main(String[] args){
        List<Integer> values = new ArrayList<>(List.of(2, 17, 17, 8, 17, 17, 17, 0, 17, 1));
        SequenceEvaluator seqEv = new SequenceEvaluator(values);

        System.out.println(ForkJoinPool.commonPool().invoke(seqEv));
    }
}
