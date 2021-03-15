package pva04.decoratorPattern;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoratorPatternTest {
    private static ShapeIF circle;
    private static ShapeIF rectangle;
    private static ShapeIF redCircle;
    private static ShapeIF redRectangle;

    @BeforeAll
    public static void initTextFixture(){
        circle = new Circle();
        rectangle = new Rectangle();
        redCircle = new RedShapeDecorator(new Circle());
        redRectangle = new RedShapeDecorator(new Rectangle());
    }

    @Test
    public void drawTest(){
        assertEquals("Draw a circle", circle.draw());
        assertEquals("Draw a rectangle", rectangle.draw());
        assertEquals("Draw a circle with a red border", redCircle.draw());
        assertEquals("Draw a rectangle with a red border", redRectangle.draw());
    }
}
