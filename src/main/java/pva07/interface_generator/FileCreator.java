package pva07.interface_generator;

import java.io.*;

/**
 * Class to create and write to file, using java.io
 */
public class FileCreator {

    public FileCreator(){}

    /**
     * Write some content to a file at a given location
     * @param path the path to the file
     * @param content the content that will be written to the file
     * @throws IOException if the file cannot be written or found
     */
    public void writeFile(String path, String content) throws IOException{
        final FileWriter writer;

        writer = new FileWriter(path);
        writer.write(content);
        writer.close();
    }
}
