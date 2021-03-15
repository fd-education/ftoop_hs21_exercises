package pva04.nullObjectPattern;

/**
 * Base class for all customers
 */
public abstract class AbstractCustomer {
    /**
     * Constraint: name must consist of strings that start with an uppercase letter and end with only lowercase letters <br>
     * Accepted separators are white space, - and '
    */
    protected static final String NAME_CHECK = "([A-Z][a-z]*)([\\s\\'-][A-Z][a-z]*)*";
    protected String name;

    public abstract String getName();

    public abstract boolean isNil();
}
