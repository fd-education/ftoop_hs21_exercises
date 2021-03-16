package pva04.decoratorPattern;

import java.util.Objects;

/**
 * Superclass for the Shape Decorators
 */
public abstract class ShapeDecorator implements ShapeIF{
    protected final ShapeIF shape;

    public ShapeDecorator(ShapeIF shape){
        this.shape = Objects.requireNonNull(shape, "Shape must not be null");
    }

    /**
     * Override the draw() method with a trivial body to serve the interface
     * @return String to mock the functionality
     */
    @Override
    public String draw(){
       return shape.draw();
    }
}
