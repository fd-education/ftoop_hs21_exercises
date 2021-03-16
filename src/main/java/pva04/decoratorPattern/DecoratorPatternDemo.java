package pva04.decoratorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that serves as a demonstration of the decorators functionality.
 */
public class DecoratorPatternDemo {
    public static void main(String[] args){
        // Instantiate a list as a container for all shapes
        // avoid having to call each draw separately
        List<ShapeIF> shapeList = new ArrayList<>();

        // Add different "ShapeIF"s to the list
        // to avoid having to call each draw separately
        shapeList.add(new Circle());
        shapeList.add(new Rectangle());
        shapeList.add(new RedShapeDecorator(new Circle()));
        shapeList.add(new RedShapeDecorator(new Rectangle()));

        // Iterate the list, call the draw() method on each element
        for(ShapeIF shape: shapeList){
            System.out.println( "\n" + shape.draw());
        }
    }
}
