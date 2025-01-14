package pva05.concurrent;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Exception wird geworfen, weil es nicht erlaubt ist, dass ein Thread über eine
 * Liste iteriert, während ein anderer gleichzeitig modifikationen daran vornimmt.
 */

class ConcurrentDemo extends Thread {

    //static ArrayList l = new ArrayList();
    static LinkedBlockingQueue<String> l = new LinkedBlockingQueue<>();

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Child Thread" +
                    " going to add element");
        }

        // Child thread trying to add new
        // element in the Collection object
        l.add("D");
    }

    public static void main(String[] args) throws InterruptedException {
        l.add("A");
        l.add("B");
        l.add("c");

        // We create a child thread that is
        // going to modify ArrayList l.
        ConcurrentDemo t = new ConcurrentDemo();
        t.start();

        // Now we iterate through the ArrayList
        // and get exception.
        Iterator itr = l.iterator();
        while (itr.hasNext()) {
            String s = (String) itr.next();
            System.out.println(s);
            Thread.sleep(6000);
        }
        System.out.println(l);
    }
}