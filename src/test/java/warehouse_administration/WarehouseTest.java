package warehouse_administration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WarehouseTest {
    static Warehouse shelflessWarehouse, sameShelvesWarehouse, varShelvesWarehouse;
    static final String expectedZeroNegativeShelvesText = "One or more shelves must be added.";
    static final String expectedZeroNegativeWeightText = "All shelves must have a weight limit bigger than 0.";

    // To test console output
    private PrintStream oldOut;
    protected ByteArrayOutputStream out;
    protected final String newLine = System.getProperty("line.separator");

    @BeforeEach
    void setUp() {
        try {
            shelflessWarehouse = new Warehouse();
            sameShelvesWarehouse = new Warehouse(20, 600);
            varShelvesWarehouse = new Warehouse(3, new double[]{2.3, 40, 35.8});
        } catch(Exception e){
            System.out.println(e);
        }

        oldOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    void constructorsCorrectTest(){
        assertEquals(0, shelflessWarehouse.getTotalStorage());
        assertEquals(0, shelflessWarehouse.getRemainingStorage());

        assertEquals(12000, sameShelvesWarehouse.getTotalStorage());
        assertEquals(12000, sameShelvesWarehouse.getRemainingStorage());

        assertEquals(78.1, varShelvesWarehouse.getTotalStorage());
        assertEquals(78.1, varShelvesWarehouse.getRemainingStorage());
    }

    @Test
    void constructorsExceptionTest(){
        /*
            Testing of first constructor overload; initializes multiple shelves with equal weight property
         */
        Exception sameZeroShelvesText = assertThrows(ZeroShelvesException.class, () -> new Warehouse(0, 1));
        assertEquals(expectedZeroNegativeShelvesText, sameZeroShelvesText.getMessage() );

        Exception sameNegativeShelvesText = assertThrows(ZeroShelvesException.class, () -> new Warehouse(-1, 1));
        assertEquals(expectedZeroNegativeShelvesText, sameNegativeShelvesText.getMessage() );

        Exception sameZeroWeightText = assertThrows(InvalidWeightException.class, () -> new Warehouse(1, 0));
        assertEquals(expectedZeroNegativeWeightText, sameZeroWeightText.getMessage() );

        Exception sameNegativeWeightText = assertThrows(InvalidWeightException.class, () -> new Warehouse(1, -1));
        assertEquals(expectedZeroNegativeWeightText, sameNegativeWeightText.getMessage() );

        /*
            Testing of second constructor overload; initializes multiple shelves with different weight properties
         */
        Exception varZeroShelvesText = assertThrows(ZeroShelvesException.class, () -> new Warehouse(0, new double[]{}));
        assertEquals(expectedZeroNegativeShelvesText, varZeroShelvesText.getMessage() );

        Exception varNegativeShelvesText = assertThrows(ZeroShelvesException.class, () -> new Warehouse(-1, new double[]{}));
        assertEquals(expectedZeroNegativeShelvesText, varNegativeShelvesText.getMessage() );

        Exception varZeroWeightText = assertThrows(InvalidWeightException.class, () -> new Warehouse(2, new double[]{0, 1}));
        assertEquals(expectedZeroNegativeWeightText, varZeroWeightText.getMessage() );

        Exception varNegativeWeightText = assertThrows(InvalidWeightException.class, () -> new Warehouse(2, new double[]{-1, 1}));
        assertEquals(expectedZeroNegativeWeightText, varNegativeWeightText.getMessage() );

        Exception varNoWeightText = assertThrows(InvalidWeightException.class, () -> new Warehouse(2, new double[]{1}));
        assertEquals(expectedZeroNegativeWeightText, varNoWeightText.getMessage() );
    }

    @Test
    void addShelfTest() {
        try {
            shelflessWarehouse.addShelf(10, 1000);
            assertEquals(10000, shelflessWarehouse.getTotalStorage());
            assertEquals(10000, shelflessWarehouse.getRemainingStorage());

            shelflessWarehouse.addShelf(1, 1);
            assertEquals(10001, shelflessWarehouse.getTotalStorage());
            assertEquals(10001, shelflessWarehouse.getRemainingStorage());
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    void addNegativeShelvesTest(){
        Exception varNegativeShelfText = assertThrows(ZeroShelvesException.class, () -> shelflessWarehouse.addShelf(-1, 1));
        assertEquals(expectedZeroNegativeShelvesText, varNegativeShelfText.getMessage() );
    }

    @Test
    void addNegativeShelfWeightsTest(){
        Exception varZeroWeightText = assertThrows(InvalidWeightException.class, () -> shelflessWarehouse.addShelf(1, 0));
        assertEquals(expectedZeroNegativeWeightText, varZeroWeightText.getMessage() );

        Exception varNegativeWeightText = assertThrows(InvalidWeightException.class, () -> shelflessWarehouse.addShelf(1, -1));
        assertEquals(expectedZeroNegativeWeightText, varNegativeWeightText.getMessage());
    }

    @Test
    void searchProduct() {
        try{
            Shelf shelf1 = new Shelf(200);
            shelf1.setShelfNumber(99);
            shelf1.storeProducts(new Product("Phone", 20), 3);
            sameShelvesWarehouse.addShelf(shelf1);

            Shelf shelf2 = new Shelf(200);
            shelf2.setShelfNumber(88);
            shelf2.storeProducts(new Product("Phone", 20), 3);
            sameShelvesWarehouse.addShelf(shelf2);

            sameShelvesWarehouse.searchProduct("Phone");
        } catch(Exception e){
            System.out.println(e);
        }

        String expectedSearchOutput = "Found your product in shelf/ shelves: " + "88, 99";

        String myOutput = out.toString();
        assertEquals(expectedSearchOutput + newLine, myOutput);
    }

    @Test
    void searchNotExistingProduct(){
        String expectedProductNotFoundOutput = "Xylophone" + "could not be found.";
        Exception productNotFoundText = assertThrows(ProductNotFoundException.class, () -> sameShelvesWarehouse.searchProduct("Xylophone"));
        assertEquals(expectedProductNotFoundOutput, productNotFoundText.getMessage() );
    }

    @AfterEach
    public void tearDown(){
        System.out.flush();
        System.setOut(oldOut); //Restore original OUT
    }
}