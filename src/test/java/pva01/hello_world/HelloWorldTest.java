package pva01.hello_world;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {
    private PrintStream oldOut;
    protected ByteArrayOutputStream out;
    protected final String newline = System.getProperty("line.separator");

    @BeforeEach
    public void setUp() {
        oldOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }


    @Test
    public void helloWorldTest(){
        String expectedText = "Hello world!";
        HelloWorld.main(null);
        String myOutput = out.toString();

        assertEquals(expectedText + newline, myOutput);
    }

    @AfterEach
    public void tearDown() {
        System.out.flush();
        System.setOut(oldOut); // Restore original OUT
    }
}
