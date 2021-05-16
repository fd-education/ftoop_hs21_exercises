package pva07.interface_generator;

public class InterfaceGenerator{ //implements Generator<Interface> {
    public static void main(String[] args) {
        try {
            String input = InputHandler.getInput();
            Class<?> clazz = Class.forName(input);
            generate(clazz);
        } catch(ClassNotFoundException ex){
            System.out.println(ex);
        }
    }

    //@Override  pva07.interface_generator.Interface
    public static Interface generate(Class<?> clazz){
        Interface iFace = new Interface(clazz.getPackageName() ,clazz.getSimpleName(), clazz.getName(), ReflectionUtils.getInterfaceMethods(clazz));

        FileCreator.writeFile(iFace.getPath(), iFace.toString());

        System.out.println(iFace);
        return iFace;
    }
}
