package pva07.interface_generator;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;

public class ReflectionUtils {
    public static Method[] getInterfaceMethods(Class<?> clazz){
        ArrayList<Method> publClassMethods = new ArrayList<>();

        for(Method method: clazz.getDeclaredMethods()){
            if(Modifier.isPublic(method.getModifiers()))
            publClassMethods.add(method);
        }

        Method[] methods = new Method[publClassMethods.size()];
        return publClassMethods.toArray(methods);
    }

    public static String getSimpleType(String type){
        String[] typeSplit = type.split("\\.");

        return typeSplit[typeSplit.length - 1];
    }
}
