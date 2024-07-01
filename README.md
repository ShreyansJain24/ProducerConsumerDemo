
# Producer Consumer

This is a simple Producer Consumer Demo fro messaging queue 

### Files in projectr and there Use
- MessageObj :- this is object which contains message and unique Id for transfering of messages this object is used
- SharedQueue :- this is shared resource in which thr producer would produce the MessageObj and then push in this shared Queue
- Producer :-this is a runnable Object which conatins core logic of generating the message and pushing the same inside the SharedQueue
- Consumer :-this is a runnable object which contains core logic to consume the messageObj from the SharedQueue.
- ExecuteMessaging :- this is the main class for the application which would simulate the console app for our Producer Consumer Demo.   

### Features

#### 1. PerformNormalTask without Logging  
- when user press 1 as per the istruction it would be prompted to provide no of cnsumers that it required to consumer data form the producer.
- once no of cnsumer is provide then user would be prompetd to provide sleep time for each consumer (note if no sleep time required press 0)
-once producer and consumer are set up then user would be show the no of successfull messages produced and consumed and erorrs encounterd after 1 min .
- after the producer and consumer thread start exactly after 1 min we terminate the executor service and fetch the current state of our shared que and fetch details regarding how many messages are produces and how many are consumed and error encounterd.
- i have also implemented a custome logic according to which when a particular message is read by all the consumer it would be removed from the queue.(note message would only be removed once it has been read by all the consumers )

#### 2. PerformNormalTask with Logging
- when pressed 2  perfforms exactly same task just provides logging.
- logging includes what are the messages that are produced and what are the messages that are being consumed and by which consumer.

### Prerequisites
- Java Development Kit (JDK) 17
- Apache Maven


### Steps to Run Locally
 1. Clone the repository to your local machine.
 2. Open the project in your preferred IDE.
 3. Configure the Tomcat server.
 4. once inside code please run ExecuteMessaging main class this would start the console app where futher instruction would be provided for runnin g the application.

## Technologies Used
- Backend: Core Java

## Future Improvements
- Feture to track all the consumed and error occured for each consumer thread .
- Adding Test cases as per requiremnt.


