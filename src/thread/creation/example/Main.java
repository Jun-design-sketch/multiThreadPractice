package thread.creation.example;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new NewThread();

        thread.start();
    }

    // MEMO: Thread already implements Runnable.
    private static class NewThread extends Thread {
        @Override
        public void run() {
            //Code that executes on the new thread
//            System.out.println("Hello from " + Thread.currentThread().getName());
            System.out.println("Hello from " + this.getName());
        }
    }
}