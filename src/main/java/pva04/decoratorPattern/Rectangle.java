package pva04.decoratorPattern;

/**
 * Class implementing ShapeIF
 * Displays a rectangle
 */
public class Rectangle implements ShapeIF {

    public Rectangle(){};

    /**
     * Draw a rectangle (mocked by String return)
     * @return String, describing the action
     */
    @Override
    public String draw(){
        return "Draw a rectangle";
    }
}
