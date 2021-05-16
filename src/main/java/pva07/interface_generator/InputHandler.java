package pva07.interface_generator;

import java.util.Scanner;

public class InputHandler {
    private Scanner scn;

    public InputHandler(){
        scn = new Scanner(System.in);
    }

    /**
     * Read one line of user input
     * @return user input
     */
    public String getInput(){
        return scn.nextLine();
    }

    /**
     * Cleanup: close the scanner
     */
    public void closeScanner(){
        scn.close();
    }
}
