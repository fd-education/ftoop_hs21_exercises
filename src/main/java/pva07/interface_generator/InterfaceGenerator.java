package pva07.interface_generator;

import java.io.IOException;

public class InterfaceGenerator{
    static Interface iFace;

    public static void main(String[] args) {
        controlFlow();
    }

    private static void controlFlow(){
        boolean running = true;

        while(running){
            String userInput = getUserInput();
            if(userInput.equalsIgnoreCase("X")) running = false;
            if(userInput.isBlank()) continue;

            try{
                Class<?> clazz = Class.forName(userInput);
                System.out.println(clazz);
                iFace = new Interface(clazz);

                iFace.generate(new FileCreator());

                System.out.println(iFace);
                System.out.println("Created interface at: " + iFace.getPath());

            } catch(ClassNotFoundException cnfEx){
                System.out.println("No class matched your input.");
            } catch(IllegalArgumentException iaEx){
                System.out.println("Please enter a valid classname");
            } catch(IOException ioEx){
                System.out.println("File could not be written to " + iFace.getPath());
            }
        }

    }

    private static String getUserInput(){
        InputHandler inputHandler = new InputHandler();

        try {
            System.out.println("\nEnter a classname to generate an interface or 'X' to quit:");
            return inputHandler.getInput();

        } catch(IllegalArgumentException iag){
            System.out.println("Please provide a valid java classname");
            return "";
        }
    }
}
