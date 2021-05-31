package pva09.kurztest.frage3;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class ClassInformation {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassInformation info = new ClassInformation();
        info.printClassDeclaration(Class.forName("java.util.ArrayList"));
    }

    public void printClassDeclaration(Class<?> clazz) {

        // Your code goes here...
        ArrayList<Class<?>> ancestors = new ArrayList<>();

        printAncestor(clazz, ancestors);

        System.out.printf("""
                Class:
                  %s
                                
                Inheritance Path:                           
                """, clazz.getName()
        );

        for(Class<?> ancestor: ancestors){
            out.println("  " + ancestor);
        }
    }

    private static void printAncestor(Class<?> c, List<Class<?>> list) {
        Class<?> ancestor = c.getSuperclass();
        if (ancestor != null) {
            list.add(ancestor);
            printAncestor(ancestor, list);
        }
    }
}
