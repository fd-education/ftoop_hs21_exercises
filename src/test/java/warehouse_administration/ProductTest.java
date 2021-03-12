package warehouse_administration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {
    static final String expectedZeroWeightText = "Product must have a weight bigger than 0";
    static Product book, rock, cup;

    @BeforeAll
    static void initTestFixture(){
        try {
            book = new Product("Book", 60);
            rock = new Product("Rock", 400);
            cup = new Product("Cup", 15);
        } catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    void getNameTest() {
        assertEquals("Book", book.getName());
        assertEquals("Rock", rock.getName());
        assertEquals("Cup", cup.getName());
    }

    @Test
    void getWeightTest() {
        assertEquals(60, book.getWeight());
        assertEquals(400, rock.getWeight());
        assertEquals(15, cup.getWeight());
    }

    @Test
    void constructorTest(){
        Exception negativeWeightText = assertThrows(InvalidWeightException.class, () -> new Product("NegativeWeightProduct", -1));
        assertEquals(expectedZeroWeightText, negativeWeightText.getMessage() );

        Exception zeroWeightText = assertThrows(InvalidWeightException.class, () -> new Product("ZeroWeightProduct", -1));
        assertEquals(expectedZeroWeightText, zeroWeightText.getMessage() );
    }
}