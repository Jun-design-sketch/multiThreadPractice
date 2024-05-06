package thread.interrupt.example2;

import java.math.BigInteger;

public class main {
    public static void main(String[] arg) {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("100000000")));
        // MEMO: Daemon -> running background, do not influence to process finish
        // ex. main thread finished, Process finished, only daemon thread keep running
        thread.setDaemon(true);
        thread.start();
        thread.interrupt();
    }
    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base+"^"+power+" = "+pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for(BigInteger i = BigInteger.ZERO ; i.compareTo(power) != 0 ; i = i.add(BigInteger.ONE)) {
                // MEMO: get interruption and stop calculation
                if(Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}
