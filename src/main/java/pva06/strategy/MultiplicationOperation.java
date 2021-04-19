package pva06.strategy;

public class MultiplicationOperation implements OperationI{

    @Override
    public double calculate(double a, double b){
        return a * b;
    }
}
