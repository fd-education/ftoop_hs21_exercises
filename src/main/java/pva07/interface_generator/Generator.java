package pva07.interface_generator;

public interface Generator<T> {
    T generate(Class<?> clazz);
}
