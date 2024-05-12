package thread.atomic.operation.exercise;
import java.util.ArrayList;

public class MinMaxMetrics {

    ArrayList<Long> storage = new ArrayList<Long>();
    Object lock = new Object();

    public MinMaxMetrics() {
        this.lock = lock;
        // Add code here
    }

    public void addSample(long newSample) {
        storage.add(newSample);
    }

    public long getMin() {
        synchronized (this.lock) {
            long min = storage.get(0);
            for(long i : storage) {
                min = min < i ? min : i;
            }
            return min;
        }
    }

    public long getMax() {
        synchronized (this.lock) {
            long max = storage.get(0);
            for(long i : storage) {
                max = max > i ? max : i;
            }
            return max;
        }
    }
}

