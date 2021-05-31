package pva09.kurztest.frage2;

import java.lang.reflect.Method;

public class Demo {

    @Author(name = "Daniel Senften")
    public void firstMethod() {
    }

    public void secondMethod() {
    }

    public static void main(String[] args) {
        Class<Demo> clazz = Demo.class;
        Method[] methods = clazz.getMethods();

        // Your code goes here...
        for(Method method: methods){
            if(method.isAnnotationPresent(Author.class)){
                Author author = method.getAnnotation(Author.class);
                System.out.printf("Method %s's author is %s", method.getName(), author.name());
            }
        }

    }

}
