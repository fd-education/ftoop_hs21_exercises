package warehouse_administration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ShelfTest {
    private final String expectedOverWeightText = "Adding these products will exceed the shelf's weight limit.";
    private final String expectedProductNotFoundText = "Can't remove that many products. Not enough products left.";
    private Shelf shelf;
    private Product product;

    // To test console output
    private PrintStream oldOut;
    protected ByteArrayOutputStream out;
    protected final String newLine = System.getProperty("line.separator");

    @BeforeEach
    void setUp() {
        try{
            shelf = new Shelf(500.77);
            product = new Product("Laptop", 100);
        } catch(Exception e){
            System.out.println(e);
        }

        //To test console output
        out = new ByteArrayOutputStream();
        oldOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void clearShelf(){
        shelf.clearShelf();
    }

    @Test
    void getterTests(){
        assertEquals(500.77, shelf.getWeightLimit());
        assertEquals(500.77, shelf.getWeightCapacity());
        assertEquals(0, shelf.getNumberOfDifferentProducts());
        assertEquals(0, shelf.getTotalNumberOfProducts());
    }

    @Test
    void containsProductTest(){
        assertFalse(shelf.containsProduct(null));
        assertFalse(shelf.containsProduct(""));
    }

    @Test
    void storeProductsTest(){
        try{
            shelf.storeProducts(product, 3);
        } catch(Exception e){
            System.out.println(e);
        }
        assertEquals(1, shelf.getNumberOfDifferentProducts());
        assertTrue(shelf.containsProduct("Laptop"));
        assertEquals(3, shelf.getTotalNumberOfProducts());
    }

    @Test
    void storeTooManyProductsTest() {
        Exception overWeightText = assertThrows(InvalidWeightException.class, () -> shelf.storeProducts(product, 6));
        assertEquals(expectedOverWeightText, overWeightText.getMessage() );

        assertEquals(0, shelf.getNumberOfDifferentProducts());
        assertEquals(0, shelf.getTotalNumberOfProducts());
    }

    @Test
    void storeNullTest(){
        assertThrows(NullPointerException.class, ()
                -> shelf.storeProducts(null, 0));
    }

    @Test
    void takeProductsTest() {
        try{
            shelf.storeProducts(product, 4);
            shelf.takeProducts(product, 2);
        } catch(Exception e){
            System.out.println(e);
        }

        String expectedTakeText = "You got " + "2" + " " + "Laptop" + "s. \n"
                + "2" + " " + "Laptop" + "s left.";
        String myOutput = out.toString();

        assertEquals(1, shelf.getNumberOfDifferentProducts());
        assertTrue(shelf.containsProduct("Laptop"));
        assertEquals(2, shelf.getTotalNumberOfProducts());


        assertEquals(expectedTakeText + newLine, myOutput);
    }

    @Test
    void takeTooManyProductsTest() {
        try{
        shelf.storeProducts(product, 4);

        Exception underWeightText = assertThrows(ProductNotFoundException.class, () -> shelf.takeProducts(product, 5));
        assertEquals(expectedProductNotFoundText, underWeightText.getMessage() );

        } catch(Exception e){
            System.out.println(e);
        }

        assertEquals(1, shelf.getNumberOfDifferentProducts());
        assertTrue(shelf.containsProduct("Laptop"));
        assertEquals(4, shelf.getTotalNumberOfProducts());
    }

    @AfterEach
    public void tearDown(){
        System.out.flush();
        System.setOut(oldOut); //Restore original OUT
    }
}