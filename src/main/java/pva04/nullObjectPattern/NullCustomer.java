package pva04.nullObjectPattern;

/**
 * Class to instantiate Null Customer objects from
 */
public class NullCustomer extends AbstractCustomer{
    public NullCustomer(){}

    @Override
    public String getName() {
        return "Not a customer.";
    }

    /**
     * @return true, as all instances of RealCustomer will be NullCustomer-Objects
     */
    @Override
    public boolean isNil() {
        return true;
    }
}
