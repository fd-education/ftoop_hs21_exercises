package pva07.interface_generator;

import lombok.Getter;

import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.util.Objects;

public class Interface {
    @Getter
    private final String path;
    private final String packageName;
    private final String simpleName;
    private final Method[] methods;

    private final static String PATH_PREFIX = "src/main/java";

    public Interface(String packageName,String simpleName, String qualifiedName, Method[] methods) throws IllegalStateException{
        validateCtorArguments(simpleName, qualifiedName, methods);

        this.packageName = packageName;
        this.simpleName = simpleName + "If";
        this.methods = methods;
        this.path = createPath(qualifiedName);
    }

    @Override
    public String toString(){

        return  "package " + packageName + ";\n"
                + "\n"
                +"public interface " + simpleName + "{\n"
                + methodsToString()
                + "}";

    }

    private String methodsToString(){
        String interfaceMethods = "";

        for(Method method : methods){
            String returnType = ReflectionUtils.getSimpleType(method.getGenericReturnType().getTypeName());
            String methodName = method.getName();
            interfaceMethods += returnType + " " + methodName + "();\n";
        }

        return interfaceMethods;
    }

    private void validateCtorArguments(String name, String qualifiedName, Method[] methods){
        Objects.requireNonNull(name, "Simple class name must not be null");
        Objects.requireNonNull(name, "Qualified class name must not be null");
        Objects.requireNonNull(methods, "Methods must not be null. Provide empty list in case of no methods.");

        if(name.isBlank()){
            throw new IllegalStateException("Class name must not be an empty String");
        }
    }

    private String createPath(String className){
        final String pathPostfix = "\\" + className.replace(".", "\\") + "If.java";

        return FileSystems.getDefault().getPath(new String(PATH_PREFIX + pathPostfix)).toAbsolutePath().toString();
    }
}
