package pva07.interface_generator;


import java.lang.reflect.*;
import java.util.ArrayList;

public class ReflectionUtils {

    private ReflectionUtils(){}

    public static Method[] getInterfaceMethods(Class<?> clazz){
        ArrayList<Method> publClassMethods = new ArrayList<>();

        for(Method method: clazz.getDeclaredMethods()){
            if(isInterfaceMethod(method, clazz)) publClassMethods.add(method);
        }

        Method[] methods = new Method[publClassMethods.size()];
        return publClassMethods.toArray(methods);
    }

    private static boolean isInterfaceMethod(Method method, Class<?> clazz){
        return hasPublicVisibility(method) && !isBaseMethod(method, clazz);
    }

    private static boolean hasPublicVisibility(Method method){
        return Modifier.isPublic(method.getModifiers());
    }

    private static boolean isBaseMethod(Method method, Class<?> clazz){
        Method[] baseMethods = clazz.getSuperclass().getDeclaredMethods();

        for(Method baseMethod: baseMethods){
            if(method.getName().equals(baseMethod.getName())) return true;
        }

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
