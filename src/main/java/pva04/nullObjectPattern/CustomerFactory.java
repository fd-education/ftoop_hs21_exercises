package pva04.nullObjectPattern;

/**
 * Factory to create real or null customer objects and validate params before doing so.
 */
public class CustomerFactory {

    /**
     * Create real or null customer objects.
     * @param name customers name; must match the patterns: "John Doe", "Jane D'Oe" or "John-Oliver Doe"
     * @return RealCustomer if the name is valid; NullCustomer if the name is invalid.
     */
    public static AbstractCustomer createCustomer(String name){
        if(!isValidName(name)) return new NullCustomer();

        return new RealCustomer(name);
    }

    // Checks whether the name matches a predefined pattern
    private static boolean isValidName(String name){
        return name.matches(AbstractCustomer.NAME_CHECK);
    }
}
