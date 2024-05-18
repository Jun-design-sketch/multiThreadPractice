package thread.io_bound_app;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IoBoundApplication {
    private static final int NUMBER_OF_TASKS = 10000;
    

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Press enter to start");
        s.nextLine();
        System.out.printf("Running %d task\n", NUMBER_OF_TASKS);
        
        long start = System.currentTimeMillis();
        performTasks();
        System.out.printf("Tasks took %dms to complete\n", System.currentTimeMillis() - start);
    }

    private static void performTasks() {
        // 動的に作業に必要なスレッドを生成する
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        try {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                //            executorService.submit(new Runnable() {
                //                @Override
                //                public void run() {
                //                    blockingIoOperation();
                //                }
                //            });
                executorService.submit(() -> blockingIoOperation());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void blockingIoOperation() {
        System.out.println("Executing a blocking task from thread: " + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
