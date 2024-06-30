package com.shreyans;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ExecuteMessaging {

    public static void main(String[] args) {
        consoleAppDisplayTasks();
    }


    private static void performNormalTask(boolean logEnabled) {
        ExecutorService executor= Executors.newFixedThreadPool(5);
        System.out.println("Please note you can have at at most 3 consumers(since size of executor service is fixed)");
        SharedQueue sharedQueue=createSharedQue();
        sharedQueue.isLogsPrint=logEnabled;
        for (int i = 0; i < sharedQueue.noOfConsumers; i++) {
            executor.submit(createConsumer(sharedQueue,i));
        }
        executor.submit(new Producer(sharedQueue));
        try{
            executor.awaitTermination(1, TimeUnit.MINUTES);
            sharedQueue.showStats();
        }catch (Exception ex){
            sharedQueue.showStats();
            System.out.println("Error occurred while terminating Thread" +ex.getMessage());
        }

    }

    private static Consumer createConsumer(SharedQueue sharedQueue, int i){
        System.out.println("Provide Thread Sleep Time for com.shreyans.Consumer"+i);
        int input=takeInput();
        int sleepTime= input>0?input:0;
        return new Consumer(sharedQueue,"com.shreyans.Consumer"+i,sleepTime);
    }

    private static SharedQueue createSharedQue(){
        System.out.println("please provide no of consumers");
        return new SharedQueue(takeInput());
    }


    private static void consoleAppDisplayTasks(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("PerformNormal Task logs disabled by pressing----- 1");
        System.out.println("PerformNormal Task logs enabled  by pressing----- 2");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        int userInput=takeInput();
        if(userInput>0){
            consoleAppPerformTask(userInput);
        }else{
            consoleAppDisplayTasks();
        }
    }

    private static void consoleAppPerformTask(int i){
           switch (i){
               case 1:
                   performNormalTask(false);
                   break;
               case 2:
                   performNormalTask(true);
                   break;
               default:
                   consoleAppDisplayTasks();
                   break;
           }
    }

    private static int takeInput(){
        int i=-1;
        Scanner sc= new Scanner(System.in);
        while(true){
            try{
                i=sc.nextInt();
            }catch (Exception ex){
                System.out.println("Invalid Input entered by user"+ex.getMessage());

            }
            return i;
        }
    }

}
