package pva03.threads;

/**
 * PVA3: Erste Schritte mit der Klasse Thread
 *
 * Exercise for threads.
 *
 * Author: Fabian Diemand
 * Version: 1.0
 */
public class PingPongThreads extends Thread{
    private static final int WORDCOUNT = 100;
    private final String word;

    /**
     * PingPongThreads parametrised with a word, that will be printed 100 times.
     * @param word some word to be printed to the console
     */
    public PingPongThreads(String word){
        this.word = word;
    }

    /**
     * Override run method of base class Thread to print the objects 'word'
     */
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            //starting at one, counting to equal to have the correct count at print
            for (int i = 1; i <= WORDCOUNT; i++) {

                //if else only to simplify recognition of different threads
                if (this.word.equals("Pong")) {
                    System.out.println("          " + (i) + ". " + word);
                } else {
                    System.out.println((i) + ". " + word);
                }

                // achieve close to perfect alternation between threads (INSECURE! Does not guarantee correct order!)
                try{
                    Thread.sleep(100);
                } catch(InterruptedException e){
                    System.out.println(e);
                }


                //System.out.println((i) + ". " + word);    //real, practical method
            }
            Thread.currentThread().interrupt();
        }
    }

    /**
     * main method that instantiates two threads and waits for their completion
     */
    public static void main(String[] args){
//        final Thread ping = new PingPongThreads("Ping");
//        final Thread pong = new PingPongThreads("Pong");

        //alternative if not interested in objects but only in threads
        new PingPongThreads("Ping").start();
        new PingPongThreads("Pong").start();

//        ping.start();
//        pong.start();

          //not necessary, as every thread terminates after 100 outputs
//        try {
//            ping.join(TimeUnit.SECONDS.toMillis(5)); //max. waiting duration redundant, only for exercise
//            pong.join(TimeUnit.SECONDS.toMillis(5)); //max. waiting duration redundant, only for exercise
//        } catch (final InterruptedException e){
//            //important to call interrupt() again in the catch clause, else the interrupt causing the exception will be 'swallowed'
//            Thread.currentThread().interrupt();
//        }
    }
}
