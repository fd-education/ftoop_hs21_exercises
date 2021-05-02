package pva06.strategy;

public class DivisionOperation implements OperationI{

    public DivisionOperation(){}

    @Override
    public double calculate(double a, double b) throws IllegalArgumentException {
        if(b == 0) throw new IllegalArgumentException("Division by 0.");

        return a / b;
    }
}
