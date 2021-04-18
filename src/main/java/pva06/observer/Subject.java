package pva06.observer;

import java.util.Observable;

public class Subject extends Observable {
    int state = 0;

    public Subject(){
        this.addObserver(new Monitor(this));
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {

        this.state = state;
        setChanged();
        notifyObservers();
    }

    //Client
    public static void main(String[] args){
        Subject subj = new Subject();

        subj.setState(15);
    }
}