package pva07.interface_generator;

import java.util.Scanner;

public class InputHandler {

    public InputHandler(){}

    public static String getInput() throws IllegalArgumentException{
        return scanInput();
    }

    private static String scanInput() throws IllegalArgumentException{
        Scanner scn = new Scanner(System.in);
        String input = "";

        input += scn.nextLine();

        scn.close();

        validateInput(input);
        return input;
    }

    private static void validateInput(String input) throws IllegalArgumentException{
        String[] inputSplit = input.split("\\.");
        String className = inputSplit[inputSplit.length - 1];

        if(!className.matches("[A-Za-z]*")){
            throw new IllegalArgumentException("Provide a valid classname");
        }
    }
}
