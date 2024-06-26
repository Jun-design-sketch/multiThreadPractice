package thread.join.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35435L, 2324L, 4656L, 23L, 2435L, 5566L);
        // We want to calculate the !0, !3435, !35435, !2324, !4656, !23, !2435, !5566
        List<FactorialThread> threads = new ArrayList<>();

        for(long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for(Thread thread : threads) {
            // background setting
            thread.setDaemon(true);
            thread.start();
        }

        // MEMO: thread.join() : when thread complete, make return
        for(Thread thread : threads) {
            // MEMO2 : when one of the thread takes lot of time, set a timer to return.
            thread.join(2000);
        }

        for(int i = 0; i < inputNumbers.size() ; i++) {
            FactorialThread factorialThread = threads.get(i);
            if(factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }
        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;
            for (long i = n; i > 0; i--) {
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
