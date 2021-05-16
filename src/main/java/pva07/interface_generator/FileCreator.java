package pva07.interface_generator;

import java.io.*;

public class FileCreator {

    public FileCreator(){}

    public void writeFile(String path, String content) throws IOException{
        final FileWriter writer;

        writer = new FileWriter(path);
        writer.write(content);
        writer.close();
    }
}
