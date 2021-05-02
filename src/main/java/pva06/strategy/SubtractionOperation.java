package pva06.strategy;

public class SubtractionOperation implements OperationI {
    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}