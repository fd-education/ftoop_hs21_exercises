package pva07.interface_generator;

import java.lang.reflect.*;
import java.util.ArrayList;

public class ReflectionUtils {

    // Private constructor because a Utility Class may not be instantiated
    private ReflectionUtils(){}

    /**
     * Only extract the public methods that are part of the class.
     * Ignore overridden methods
     * @param clazz Class reflection
     * @return methods to put into an interface
     */
    public static Method[] getInterfaceMethods(Class<?> clazz){
        ArrayList<Method> publClassMethods = new ArrayList<>();

        for(Method method: clazz.getDeclaredMethods()){
            if(isInterfaceMethod(method, clazz)) publClassMethods.add(method);
        }

        Method[] methods = new Method[publClassMethods.size()];
        return publClassMethods.toArray(methods);
    }

    // Check if a methods is public and not a method of a super class
    private static boolean isInterfaceMethod(Method method, Class<?> clazz){
        return hasPublicVisibility(method) && !isOverrideMethod(method, clazz);
    }

    // Check if the modifier is "public"
    private static boolean hasPublicVisibility(Method method){
        return Modifier.isPublic(method.getModifiers());
    }

    // Check if the method is overriding a base method
    private static boolean isOverrideMethod(Method method, Class<?> clazz){
        Method[] baseMethods = clazz.getSuperclass().getDeclaredMethods();

        // Check for abstract classes and other super classes
        for(Method baseMethod: baseMethods){
            if(method.getName().equals(baseMethod.getName())) return true;
        }

        // Check for interface methods
        Class<?>[] iFaces = clazz.getInterfaces();
        for(Class<?> iFace: iFaces){
            Method[] iMethods = iFace.getMethods();

            for(Method m: iMethods){
                if(method.getName().equals(m.getName())) return true;
            }
        }

        return false;
    }
}
