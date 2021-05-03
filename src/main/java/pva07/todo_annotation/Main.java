package pva07.todo_annotation;

public class Main {
    public static void main(String[] args){
        Dummy dummy = new Dummy();
        TodoHandler todoHandler = new TodoHandler();

        todoHandler.getTodos(dummy.getClass());
    }
}
