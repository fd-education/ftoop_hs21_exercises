package pva06.observer;

public class Client {
    private static Subject subject1 = new Subject();
    private static Subject subject2 = new Subject();

    private static ObserverI monitor1 = new Monitor(subject1);
    private static ObserverI monitor2 = new Monitor();

    public static void main(String[] args){
        subject2.addObserver(monitor2);

        subject1.setState(5);
        subject2.setState(100);
    }
}
