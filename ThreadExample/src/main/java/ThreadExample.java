import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadExample {

    static final Object monitor = new Object();
    static AtomicInteger counter = new AtomicInteger(0);
    static Thread thread = new Thread();

    static final int NUM_ITERATIONS = 100000;
    static final int NUM_THREADS = 10;
    static ArrayList<Thread> threads = new ArrayList<>();

    static SpinLock spinLock = new SpinLock();

    public static void main(String[] args) {
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int i = 0; i < NUM_ITERATIONS; i++) {

                        counter = counter.addAndGet(1);

                    }

                }
            });
            threads.add(t);
        }

        long timeStart = System.currentTimeMillis();
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
        timeStart = System.currentTimeMillis() - timeStart;
        System.out.println(timeStart);
    }


}

class SpinLock {
    AtomicInteger flag = new AtomicInteger(0);

    public void unlock() {
        flag.set(0);
    }

    public void lock() {
        while (!flag.compareAndSet(0, 1)) ;

    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("hui");
    }
}