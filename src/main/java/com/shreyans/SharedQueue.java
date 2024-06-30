package com.shreyans;

import java.util.*;

public class SharedQueue {
    public final Deque<MessageObj> messageQueue = new ArrayDeque<>();
    private final Map<String, Set<String>> messageTracker = new HashMap<>();

    public final int noOfConsumers;

    private int totalMessagePushed = 0;
    private int totalMessageCosumed= 0;

    private int errorWhilePushing=0;
    private int errorWhileConsuming=0;

    public boolean isLogsPrint = false;

    public SharedQueue(int noOfConsumers) {
        this.noOfConsumers = noOfConsumers;
    }

    public synchronized void addMessageInQueue(MessageObj msgObj) {
        messageQueue.addLast(msgObj);
        if (isLogsPrint) {
            System.out.println("Message added to MessageQueue >>>>>>>" + msgObj.getMessage());
        }
        messageTracker.put(msgObj.getMsgId(), new HashSet<>());
        totalMessagePushed++;
        notifyAll();
    }

    public synchronized MessageObj consumeMessageFromQueue(String consumerId) throws Exception {
        while (true) {
            for (MessageObj msgObj : messageQueue) {
                //check if the consumer is not present in the
                if (!messageTracker.get(msgObj.getMsgId()).contains(consumerId)) {
                    if (isLogsPrint) {
                        System.out.print("Message read >>>>>>" + msgObj.getMessage());
                        System.out.println(">>>>by " + consumerId);
                    }
                    messageTracker.get(msgObj.getMsgId()).add(consumerId);
                    if (messageTracker.get(msgObj.getMsgId()).size() >= noOfConsumers) {
                        totalMessageCosumed++;
                        messageQueue.remove(msgObj);   //remove the message when read by all the consumers
                        messageTracker.remove(msgObj.getMsgId());  //remove from message tracker as well
                    }
                    notifyAll();
                    return msgObj;
                }
            }
            //once all message are finished reading wait
            wait();
        }
    }

    public synchronized void logPushingError(){
        errorWhilePushing++;
    }

    public synchronized void logConsumingError(){
        errorWhileConsuming++;
    }

    public synchronized void showStats(){
        System.out.println("Total message Pushed "+totalMessagePushed);
        System.out.println("Total message Consumed "+totalMessageCosumed);
        System.out.println("Error Occured while Pushing "+errorWhilePushing);
        System.out.println("Error Occured while Consuming "+errorWhileConsuming);
    }
}
