package pva06.strategy;

import java.util.concurrent.ThreadLocalRandom;

public final class StrategyPattern {

    public static void main(String[] args) {
        double a = ThreadLocalRandom.current().nextDouble(-100., 100.);
        double b = ThreadLocalRandom.current().nextDouble(1., 100.);

        Calculator calculator = new Calculator(new AdditionOperation());
        Calculator calculator1 = new Calculator(new SubtractionOperation());
        Calculator calculator2 = new Calculator(new MultiplicationOperation());
        Calculator calculator3 = new Calculator(new DivisionOperation());
        double result = calculator.calculate(a, b);

        System.out.format("     a = %8.2f%n", a);
        System.out.format("     b = %8.2f%n", b);
        System.out.format("-----------------%n");
        System.out.format("result = %8.2f%n", result);
    }
}