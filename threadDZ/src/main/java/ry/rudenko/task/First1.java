package ry.rudenko.task;


import java.util.Stack;

public class First1 {

  public static void main(String[] args) {
    new Start().starter();
    System.out.println(Thread.currentThread().getName() + " Stopped");
  }
}

class Start {

  public void starter() {
    System.out.println(Thread.currentThread().getName() + " RUNNING");
    String massage = "Hello from thread ";
    Stack<Thread> threadStack = new Stack<>();
    for (int i = 0; i < 50; i++) {
      try {
        threadStack.push(new Thread(new FirstThread(massage + i)));
      } catch (InterruptedException e) {
        System.err.println("Thread was interrupted");
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
      }
    }
    for (int i = 0; i < 50; i++) {
      final Thread pop = threadStack.pop();
      pop.start();
      try {
        pop.join();
      } catch (InterruptedException e) {
        System.err.println("Thread was interrupted");
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
      }
    }
  }
}

class FirstThread extends Thread {

  FirstThread(String name) throws InterruptedException {
    super(name);
  }

  @Override
  public void run() {
    System.out.println(getName());
  }
}