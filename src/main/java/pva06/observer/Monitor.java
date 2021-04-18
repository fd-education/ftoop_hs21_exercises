package pva06.observer;

import java.util.Observable;
import java.util.Observer;

public class Monitor implements Observer {

    private Subject subject;

    public Monitor(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void update(Observable o, Object state) {
        System.out.println("The state of the subject has changed to " + ((Subject) o).getState() + ".");
    }
}