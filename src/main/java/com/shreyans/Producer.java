package com.shreyans;

public class Producer implements Runnable {
    private final SharedQueue sharedQueue;
    private int totalMessagePushed = 0;

    private int totalErrors = 0;

    private boolean isAllDataPushed = false;

    public Producer(SharedQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            String message = "testMsg" + i;
            try {
                sharedQueue.addMessageInQueue(new MessageObj(message));
            } catch (Exception ex) {
                System.out.println("com.shreyans.Producer Thread encountered error while pushing :-" + message);
                sharedQueue.logPushingError();
            }
        }
    }
}
