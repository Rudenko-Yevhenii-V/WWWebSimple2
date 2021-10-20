package ry.rudenko.task;


import java.util.concurrent.atomic.AtomicInteger;

public class First4 {

  public static void main(String[] args) {
    AtomicInteger atomicInteger = new AtomicInteger(49);
    System.out.println(Thread.currentThread().getName() + " RUNNING");
    for (int i = 0; i < 50; i++) {
      try {
        String massage = "Hello from thread ";
        new nextThread(massage + i, i, atomicInteger).start();
      } catch (InterruptedException e) {
        System.out.println("Thread was interrupted");
      }
    }
  }
}

class nextThread extends Thread {

  private final Integer i;
  private final AtomicInteger atomicI;
  private boolean isWaiting = true;

  nextThread(String name, Integer i, AtomicInteger atomicI) throws InterruptedException {
    super(name);
    this.i = i;
    this.atomicI = atomicI;
    System.out.println(this.getName() + " Created");
  }

  @Override
  public void run() {
    synchronized (atomicI) {
      while (isWaiting) {
        if (i != atomicI.get()) {
          try {
            atomicI.wait(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else {
          isWaiting = false;
          System.out.println(getName() + " started");
          atomicI.decrementAndGet();
          atomicI.notify();
        }
      }
    }
  }
}














