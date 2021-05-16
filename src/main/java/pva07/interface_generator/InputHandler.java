package pva07.interface_generator;

import java.util.Scanner;

public class InputHandler {

    public InputHandler(){}

    public String getInput() throws IllegalArgumentException{
        return scanInput();
    }

    private String scanInput() throws IllegalArgumentException{

        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        validateInput(input);

        return input;
    }

    private void validateInput(String input) throws IllegalArgumentException{
        String[] inputSplit = input.split("\\.");
        String className = inputSplit[inputSplit.length - 1];

        if(!className.matches("[A-Za-z]*")){
            throw new IllegalArgumentException("Provide a valid classname");
        }
    }
}
