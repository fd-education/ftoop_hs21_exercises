package warehouse_administration;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String exceptionText){
        super(exceptionText);
    }
}
