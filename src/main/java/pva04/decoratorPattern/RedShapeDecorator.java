package pva04.decoratorPattern;

import java.util.Objects;

/**
 * Decorator for Objects of type ShapeIF
 * Provides a red border as a part of the draw method
 */
public class RedShapeDecorator extends ShapeDecorator {

    /**
     * Constructor to receive the Object to be decorated, passing it on to ShapeDecorator after doing a NULL check
     * @param shape Object of type ShapeIF
     */
    public RedShapeDecorator(ShapeIF shape){
        super(shape);
    }

    /**
     * Override the draw method to add a red border to all shapes using the decorator
     * @return String concatenation of the draw() return and the setRedBorder() return
     */
    @Override
    public String draw(){
        return shape.draw() + setRedBorder();
    }

    /**
     * Set a red border to a ShapeIF object
     * @return String to mock functionality
     */
    public String setRedBorder(){
        return " with a red border";
    }
}
