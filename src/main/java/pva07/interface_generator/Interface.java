package pva07.interface_generator;

import lombok.Getter;

import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;

/**
 * Class to represent a Java Interface
 */
@Getter
public class Interface implements Generator<Interface>{
    private final String path;
    private final String packageName;
    private final String simpleName;
    private final Method[] methods;
    private final String[] imports;

    public Interface(Class<?> clazz){
        this.packageName = clazz.getPackageName();
        this.simpleName = clazz.getSimpleName() + "If";
        this.methods = ReflectionUtils.getInterfaceMethods(clazz);
        this.imports = detectImports(ReflectionUtils.getInterfaceMethods(clazz));
        this.path = createPath(clazz.getName());
    }

    /**
     * Gives back a String representation of an Interface
     * @return String containing a working interface
     */
    @Override
    public String toString(){
        return  "package " + packageName + ";\n"
                + "\n"
                + importsToString()
                + "\n"
                +"public interface " + simpleName + " {\n"
                + methodsToString()
                + "}";
    }

    /**
     * Create a working java interface at the location of its corresponding class.
     * @param fileCreator fileCreator object
     * @return a representation of the interface just written to a file
     * @throws IOException if file cannot be written
     */
    @Override
    public Interface generate(FileCreator fileCreator) throws IOException {
        fileCreator.writeFile(this.path, this.toString());
        return this;
    }

    // Create a path to the location of the class, that will act as the interfaces location
    private String createPath(String className){
        String path = String.format("src\\main\\java\\%sIf.java", className.replace(".", "\\"));

        return FileSystems.getDefault().getPath(path).toAbsolutePath().toString();
    }

    // Detect complex data types, that are required to be imported before usage
    private String[] detectImports(Method[] methods){
        ArrayList<String> types = new ArrayList<>();

        for(Method method: methods){

            // Detect complex return types
            Class<?> returnType = method.getReturnType();
            if(complexAndNotExisting(returnType, types)) types.add(getPackageName(returnType));

            // Detect complex parameter types
            Parameter[] params = method.getParameters();
            for(Parameter param: params){
                Class<?> paramType = param.getType();

                if(complexAndNotExisting(paramType, types)) types.add(getPackageName(paramType));
            }

            // Detect exceptions to be imported
            Class<?>[] exceptions = method.getExceptionTypes();
            for(Class<?> exceptionType: exceptions){
                if(complexAndNotExisting(exceptionType, types)) types.add(getPackageName(exceptionType));
            }
        }

        String[] typeImports = new String[types.size()];
        return types.toArray(typeImports);
    }

    // Returns true, if a provided datatype is complex and not already part of an import list
    private boolean complexAndNotExisting(Class<?> type, ArrayList<String> types){
        return !type.isPrimitive() && !types.contains(getPackageName(type));
    }

    // Create an importable package name
    private String getPackageName(Class<?> type){
        return String.format("%s.%s", type.getPackageName(), type.getSimpleName().replaceAll("(<.*>)|(\\[])", ""));
    }

    // Create a String representation of a classes imports
    private String importsToString(){
        StringBuilder importStatements = new StringBuilder();

        for(String imp : imports){
            importStatements.append(String.format("import %s;\n", imp));
        }

        return importStatements.toString();
    }

    // Create a String representation of class methods
    private String methodsToString(){
        StringBuilder methodString = new StringBuilder();

        for(Method method : methods){
            String returnType = method.getReturnType().getSimpleName();
            String methodName = method.getName();

            Class<?>[] exceptions = method.getExceptionTypes();

            Parameter[] params = method.getParameters();

            methodString.append(String.format("\t%s %s (%s) %s;\n", returnType, methodName, parameterListToString(params), exceptionsToString(exceptions)));
        }

        return methodString.toString();
    }

    // Create a String representation of methods parameter list
    private String parameterListToString(Parameter[] params){
        StringBuilder paramString = new StringBuilder();

        if(params.length > 0) {
            for (int i = 0; i < params.length - 1; i++) {
                paramString.append(String.format("%s, ", parameterToString(params[i])));
            }

            paramString.append(parameterToString(params[params.length - 1]));
        }

        return paramString.toString();
    }

    // Create a String representation of a single parameter
    private String parameterToString(Parameter param){
        String fullType = param.getParameterizedType().getTypeName();
        String[] typeSplit = fullType.split("\\.");

        String type = typeSplit.length  > 0 ? typeSplit[typeSplit.length - 1] : fullType;

        return String.format("%s %s", type, param.getName());
    }

    // Create a String representation of exceptions
    private String exceptionsToString(Class<?>[] exceptions){
        StringBuilder exception = new StringBuilder();

        if(exceptions.length > 1) {
            exception.append("throws");

            for (int i = 0; i < exceptions.length - 1; i++) {
                exception.append(String.format("%s,", exceptions[i].getSimpleName()));
            }
            return exception.append(String.format("%s;", exceptions[exceptions.length - 1].getSimpleName())).toString();

        } else if(exceptions.length == 1){
            return exception.append(String.format("throws %s", exceptions[0].getSimpleName())).toString();
        } else {
            return exception.toString();
        }
    }
}
