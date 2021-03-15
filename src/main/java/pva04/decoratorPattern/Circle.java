package pva04.decoratorPattern;

/**
 * Class implementing ShapeIF
 * Displays a circle
 */
public class Circle implements ShapeIF {

    public Circle(){};

    /**
     * Draw a circle (mocked by String return)
     * @return String, describing the action
     */
    @Override
    public String draw(){
        return "Draw a circle";
    }

}
