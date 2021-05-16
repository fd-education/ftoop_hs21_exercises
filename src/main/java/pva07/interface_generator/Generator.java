package pva07.interface_generator;

import java.io.IOException;

/**
 * Generator interface
 * @param <T> Type of resource to generate
 */
public interface Generator<T> {
    T generate(FileCreator fileCreator) throws IOException;
}
