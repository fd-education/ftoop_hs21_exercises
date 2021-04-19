package pva06.strategy;

public class AdditionOperation implements OperationI{

    @Override
    public double calculate(double a, double b) throws IllegalArgumentException {
        return a + b;
    }
}