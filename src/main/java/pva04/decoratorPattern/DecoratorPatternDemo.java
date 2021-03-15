package pva04.decoratorPattern;

import java.util.ArrayList;
import java.util.List;

public class DecoratorPatternDemo {
    public static void main(String[] args){
        List<ShapeIF> shapeList = new ArrayList<>();

        shapeList.add(new Circle());
        shapeList.add(new Rectangle());
        shapeList.add(new RedShapeDecorator(new Circle()));
        shapeList.add(new RedShapeDecorator(new Rectangle()));

        for(ShapeIF shape: shapeList){
            System.out.println( "\n" + shape.draw());
        }
    }
}
