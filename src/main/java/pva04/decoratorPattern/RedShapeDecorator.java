package pva04.decoratorPattern;

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(ShapeIF shape){
        super(shape);
    }

    @Override
    public String draw(){
        return shape.draw() + setRedBorder();
    }

    public String setRedBorder(){
        return " with a red border";
    }
}
