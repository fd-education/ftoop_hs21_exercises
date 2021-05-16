package pva07.interface_generator;

import lombok.Getter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.FileSystems;
import java.util.ArrayList;

@Getter
public class Interface implements Generator<Interface>{
    private  final String path;
    private  final String packageName;
    private  final String simpleName;
    private final Method[] methods;
    private final String[] imports;

    public Interface(Class<?> clazz) throws IllegalStateException{
        this.packageName = clazz.getPackageName();
        this.simpleName = clazz.getSimpleName() + "If";
        this.methods = ReflectionUtils.getInterfaceMethods(clazz);
        this.imports = getTypeImports(ReflectionUtils.getInterfaceMethods(clazz));
        this.path = createPath(clazz.getName());

        getTypeImports(this.methods);
    }

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

    @Override
    public Interface generate(FileCreator fileCreator) throws IOException {
        fileCreator.writeFile(this.path, this.toString());
        return this;
    }

    private String methodsToString(){
        String interfaceMethods = "";

        for(Method method : methods){
            String returnType = method.getReturnType().getSimpleName();
            String methodName = method.getName();

            Class<?>[] exceptions = method.getExceptionTypes();

            Parameter[] params = method.getParameters();

            interfaceMethods += returnType + " " + methodName + "("+ paramsToString(params) + ")" + exceptionsToString(exceptions) + ";\n";
        }

        return interfaceMethods;
    }

    private String importsToString(){
        String importStatements = "";

        for(String i : imports){
            importStatements += "import " + i + ";\n";
        }

        return importStatements;
    }

    private String paramsToString(Parameter[] params){
        if(params.length > 0) {
            String paramString = "";
            for (int i = 0; i < params.length - 1; i++) {
                paramString += paramToString(params[i]) + ", ";

            }
            paramString += paramToString(params[params.length - 1]);

            System.out.println(paramString);
            return paramString;
        }

        return "";
    }

    private String createPath(String className){
        String pathPrefix = "src/main/java";
        final String pathPostfix = "\\" + className.replace(".", "\\") + "If.java";

        return FileSystems.getDefault().getPath(pathPrefix + pathPostfix).toAbsolutePath().toString();
    }

    private String[] getTypeImports(Method[] methods){
        ArrayList<String> types = new ArrayList<>();

        for(Method method: methods){
            Class<?> returnType = method.getReturnType();

            if(complexAndNotExisting(returnType, types)) types.add(typeToString(returnType));

            Class<?>[] exceptions = method.getExceptionTypes();
            for(Class<?> exceptionType: exceptions){
                if(complexAndNotExisting(exceptionType, types)) types.add(typeToString(exceptionType));
            }

            Parameter[] params = method.getParameters();
            for(Parameter param: params){
                Class<?> paramType = param.getType();

                if(complexAndNotExisting(paramType, types)) types.add(typeToString(paramType));
            }
        }

        String[] typeImports = new String[types.size()];
        return types.toArray(typeImports);
    }

    private boolean complexAndNotExisting(Class<?> type, ArrayList<String> types){
        return !type.isPrimitive() && !types.contains(typeToString(type));
    }

    private String typeToString(Class<?> type){
        return type.getPackageName() + "." +  type.getSimpleName().replaceAll("(<.*>)|(\\[\\])", "");
    }

    private String paramToString(Parameter param){
        String[] typeSplit = param.getParameterizedType().getTypeName().split("\\.");

        String type = typeSplit.length  > 0 ? typeSplit[typeSplit.length - 1] : typeSplit[0];

        System.out.println(type + " " + param.getName());

        return type + " " + param.getName();
    }

    private String exceptionsToString(Class<?>[] exceptions){

        if(exceptions.length > 1) {
            String exceptionString = " throws ";

            for (int i = 0; i < exceptions.length - 1; i++) {
                exceptionString += exceptions[i].getSimpleName() + ", ";
            }

            exceptionString += exceptions[exceptions.length - 1].getSimpleName();

            return exceptionString;
        } else if(exceptions.length == 1){
            return " throws " + exceptions[0].getSimpleName();
        } else {
            return "";
        }

    }
}
