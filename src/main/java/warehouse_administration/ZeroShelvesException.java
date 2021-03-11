package warehouse_administration;

public class ZeroShelvesException extends Exception{
    public ZeroShelvesException(){
        super("One or more shelves must be added.");
    }
}
