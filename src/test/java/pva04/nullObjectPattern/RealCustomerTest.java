package pva04.nullObjectPattern;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RealCustomerTest {
    private static AbstractCustomer johnDoe, janeDoe;

    private static AbstractCustomer uppercaseWithinName, lowercaseName, numericName, numericLetterName;

    private static final String NULL_OBJECT_NAME = "Not a customer.";


    @BeforeAll
    public static void initTextFixture(){
        //valid instances
        johnDoe = CustomerFactory.createCustomer("John Doe");
        janeDoe = CustomerFactory.createCustomer("Jane Doe");

        //invalid instances
        uppercaseWithinName = CustomerFactory.createCustomer("UpperCaseName ");
        lowercaseName = CustomerFactory.createCustomer("lowercase Name");
        numericName = CustomerFactory.createCustomer("123");
        numericLetterName = CustomerFactory.createCustomer("Name123");
    }

    @Test
    public void getNameTestPositive(){
        assertEquals("John Doe", johnDoe.getName());
        assertEquals("Jane Doe", janeDoe.getName());
    }

    @Test
    public void getNameTestNegative(){
        assertEquals(NULL_OBJECT_NAME, uppercaseWithinName.getName());
        assertEquals(NULL_OBJECT_NAME, lowercaseName.getName());
        assertEquals(NULL_OBJECT_NAME, numericName.getName());
        assertEquals(NULL_OBJECT_NAME, numericLetterName.getName());
    }

    @Test
    public void isNilTestPositive(){
        assertFalse(johnDoe.isNil());
        assertFalse(janeDoe.isNil());
    }

    @Test
    public void isNilTestNegative(){
        assertFalse(uppercaseWithinName.isNil());
        assertTrue(lowercaseName.isNil());
        assertTrue(numericName.isNil());
        assertTrue(numericLetterName.isNil());
    }
}
