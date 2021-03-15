package pva04.decoratorPattern;

/**
 * Superclass for the Shape Decorators
 */
public class ShapeDecorator implements ShapeIF{
    protected final ShapeIF shape;

    public ShapeDecorator(ShapeIF shape){
        this.shape = shape;
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
