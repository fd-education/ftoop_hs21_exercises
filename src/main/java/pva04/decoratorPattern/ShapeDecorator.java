package pva04.decoratorPattern;

import java.util.Objects;

public class ShapeDecorator implements ShapeIF{
    protected final ShapeIF shape;

    public ShapeDecorator(ShapeIF shape){
        this.shape = Objects.requireNonNull(shape, "Shape must not be null");
    }

    @Override
    public String draw(){
       return shape.draw();
    }
}
