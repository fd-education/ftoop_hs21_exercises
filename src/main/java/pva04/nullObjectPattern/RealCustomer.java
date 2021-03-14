package pva04.nullObjectPattern;

/**
 * Class to instantiate valid Customer objects from
 */
public class RealCustomer extends AbstractCustomer{

    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * @return false, as all instances of RealCustomer will be valid Customer-Objects
     */
    @Override
    public boolean isNil() {
        return false;
    }
}
