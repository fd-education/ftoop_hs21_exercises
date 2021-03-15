package pva04.decoratorPattern;

public class Rectangle implements ShapeIF {

    public Rectangle(){};

    @Override
    public String draw(){
        return "Draw a rectangle";
    }
}
