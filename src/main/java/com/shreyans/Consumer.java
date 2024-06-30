package com.shreyans;

public class Consumer implements Runnable {
    private final SharedQueue sharedQueue;
    private final int threadSleepTime;
    private final String consumerId;

    public Consumer(SharedQueue sharedQueue, String consumerId, int threadSleepTime) {
        this.sharedQueue = sharedQueue;
        this.threadSleepTime = threadSleepTime;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        MessageObj msgObj = null;
        try {
            while (true) {
                msgObj = sharedQueue.consumeMessageFromQueue(consumerId);
                if (threadSleepTime > 0) {
                    Thread.sleep(threadSleepTime);
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception in consuming messages" + msgObj + " by" + consumerId);
            sharedQueue.logConsumingError();
        }
    }

}
