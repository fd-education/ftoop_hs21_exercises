package pva07.interface_generator;

import java.io.IOException;

public interface Generator<T> {
    T generate(FileCreator fileCreator) throws IOException;
}
