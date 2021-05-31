package pva07.interface_generator;

import java.io.IOException;

/**
 * Class to generate a working interface corresponding to a given class
 */
public class InterfaceGenerator{
    private static Interface iFace;
    private static InputHandler inputHandler;

    public static void main(String[] args) {
        controlFlow();
    }

    // Orchestrator method for interface creation
    private static void controlFlow(){

        while(true){

            try{
                String userInput = getUserInput();

                // Terminate if user enters 'X'
                if(userInput.equalsIgnoreCase("X")) break;
                // Skip logic if user enters an empty string
                if(userInput.isBlank()) continue;

                // Use Class reflection to instantiate Interface object
                Class<?> clazz = Class.forName(userInput);
                System.out.println("\nFound class: " + clazz);
                iFace = new Interface(clazz);

                // Create Interface file
                iFace.generate(new FileCreator());

                System.out.println("Created interface at: " + iFace.getPath());

            } catch(ClassNotFoundException cnfEx){
                System.out.println("\nNo class matched your input.");
            } catch(IOException ioEx){
                System.out.println("\nFile could not be written to " + iFace.getPath());
            }
        }

        cleanup();
    }

    // Ask for user input
    private static String getUserInput(){
        System.out.println("\nEnter a classname to generate an interface or 'X' to quit:");

        inputHandler = new InputHandler();

        return inputHandler.getInput();
    }

    // Cleanup before program exits
    private static void cleanup(){
        inputHandler.closeScanner();
    }
}
