package thread.virtual_thread;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class VirtualThreadsDemo {
    private static final int NUMBER_OF_VIRTUAL_THREADS = 2;

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Inside thread: " + Thread.currentThread());
        List<Thread> virtualThreads = new ArrayList<>();

        for(int i = 0; i < NUMBER_OF_VIRTUAL_THREADS; i++) {
            Thread virtualThread = Thread.ofVirtual().unstarted(runnable);
            virtualThreads.add(virtualThread);
        }

        for(Thread virtualThread : virtualThreads) {
            virtualThread.start();
        }

        for(Thread virtualThread : virtualThreads) {
            virtualThread.join();
        }
    }
}
