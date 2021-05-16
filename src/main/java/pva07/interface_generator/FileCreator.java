package pva07.interface_generator;

import java.io.*;

public class FileCreator {

    public static void writeFile(String path, String content){
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.close();
        } catch(IOException ex){
            System.out.println(ex);
        }
    }
}
