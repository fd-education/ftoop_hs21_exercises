package pva06.strategy;

public class Calculator {
    private final OperationI operation;

    public Calculator(OperationI operation) {
        this.operation = operation;
    }

    public double calculate(double a, double b) {
        return operation.calculate(a, b);
    }
}