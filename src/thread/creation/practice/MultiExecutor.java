package thread.creation.practice;
import java.util.List;
import java.util.ArrayList;

public class MultiExecutor {

    // Add any necessary member variables here
    public List<Thread> threads = new ArrayList<>();

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        // Complete your code here
        for(Runnable task: tasks){
            threads.add(new Thread(task));
        }
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        for(Thread thread : threads) {
            thread.start();
        }
    }
}