package pva06.observer;

public class Monitor implements ObserverI{
    private Subject subject;

    public Monitor(){};

    public Monitor(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update(ObservableI observable){
        System.out.println("State of " + observable + " has changed to: " + observable.getState());
    }
}
