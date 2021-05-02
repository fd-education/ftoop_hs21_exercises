package pva06.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject implements ObservableI {
    int state = 0;
    private List<ObserverI> observers = new ArrayList<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public void addObserver(ObserverI observer){
       observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverI observer){
        observers.remove(observer);
    }

    @Override
    public synchronized void notifyObservers(){
        for(ObserverI observer: observers){
            observer.update(this);
        }
    }
}
