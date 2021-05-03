package pva07.todo_annotation;

@Todo(when=1, what="Clean up code")
public class Dummy {

    @Todo(when=0, what="Write some code")
    private void dummyMethod(){
    }

    private void dummyWithoutAnnotation(){
    }
}
