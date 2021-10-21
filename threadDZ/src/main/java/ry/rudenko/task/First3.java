package ry.rudenko.task;


import java.util.concurrent.LinkedBlockingDeque;

public class First3 {

  public static void main(String[] args) {
    String massage = "Hello from thread ";
    var lambdaContext = new Object() {
      Integer count = 0;
    };
    var queue = new LinkedBlockingDeque<Thread>(50);
    var producer = new Thread(() -> {
      while (lambdaContext.count < 50) {
        queue.add(new Thread(new TaskThread(massage + lambdaContext.count)));
        lambdaContext.count++;
      }
    });

    var consumer = new Thread(() -> {
      Boolean isRun = false;

      System.out.println(
          "(queue.size() != 49 || isRun)&&queue.isEmpty() = " + ((queue.size() != 49 || isRun)
              && queue.isEmpty()));
      while ((queue.size() != 49 || isRun) && !queue.isEmpty()) {
        try {
          final Thread take = queue.takeLast();
          take.start();
          take.join();
          isRun = true;
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    producer.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    consumer.start();
  }
}

class TaskThread extends Thread {

  TaskThread(String name) {
    super(name);
    System.out.println(this.getName() + " RUNNING");
  }

  @Override
  public void run() {
    System.out.println(getName());
  }
}


















