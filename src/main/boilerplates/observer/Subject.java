package ch.ffhs.ftoop.observer;

public class Subject {
    int state = 0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}