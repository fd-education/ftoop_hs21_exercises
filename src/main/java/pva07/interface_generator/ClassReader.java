package pva07.interface_generator;

import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ClassReader {


    //pva07.interface_generator.Interface
    public static void readClassInformation(String className){
        try{
            final Class<?> clazz = Class.forName(className);
            clazz.getMethods();


        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}
