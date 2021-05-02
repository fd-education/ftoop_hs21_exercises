package pva06.observer;

import java.util.List;

public interface ObservableI {
    void addObserver(ObserverI o);

    void removeObserver(ObserverI o);

    void notifyObservers();

    int getState();
}
