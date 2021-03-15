package pva04.decoratorPattern;

public class Circle implements ShapeIF {

    public Circle(){};

    @Override
    public String draw(){
        return "Draw a circle";
    }

}
