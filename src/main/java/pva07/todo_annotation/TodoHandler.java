package pva07.todo_annotation;

import java.lang.reflect.*;

public class TodoHandler {
    public TodoHandler(){}

    public void getTodos(Class<?> clazz){
        getClassTodos(clazz);
        getMethodTodos(clazz.getDeclaredMethods());
    }

    private void getClassTodos(Class<?> clazz){
        if(clazz.isAnnotationPresent(Todo.class)){
            Todo todo = clazz.getAnnotation(Todo.class);

            Target target = clazz.isInterface() ? Target.INTERFACE : Target.CLASS;
            printAnnotationInfo(target, clazz.getName(), todo.when(), todo.what());
        }
    }

    private void getMethodTodos(Method[] methods){
        for(Method method: methods){
            if(method.isAnnotationPresent(Todo.class)){
                Todo todo = method.getAnnotation(Todo.class);

                method.setAccessible(true);
                printAnnotationInfo(Target.METHOD, method.getReturnType() + method.getName(), todo.when(), todo.what());
            }
        }
    }

    private void printAnnotationInfo(Target target, String name, int when, String what ){
        System.out.printf("Todo at: %s %s | until: %d | Task: %s \n", target, name, when, what);
    }

    enum Target{
        CLASS("Class"),
        INTERFACE("Interface"),
        METHOD("Method");

        private final String name;

        Target(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }
}


