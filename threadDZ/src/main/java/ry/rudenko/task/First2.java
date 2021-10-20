package ry.rudenko.task;



public class First2 {

  public static void main(String[] args) {
    System.out.println(Thread.currentThread().getName() + " RUNNING");
    for (int i = 0; i < 50; i++) {

      try {
        String massage = "Hello from thread ";
        new FirstTaskThread(massage + i, i).start();
      } catch (InterruptedException e) {
        System.err.println("Thread was interrupted");
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
      }
    }
  }
}
class FirstTaskThread extends Thread {
  int i;
  FirstTaskThread(String name, int i) throws InterruptedException {
    super(name);
    this.i= i;
    System.out.println(this.getName() + " RUNNING");
  }

  @Override
  public void run() {
    try {
      Thread.sleep(100L * (50 - i));
    } catch (InterruptedException e) {
      System.err.println("Thread was interrupted");
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
    System.out.println("Hello from " + getName());
  }

}
//  Напишите приложение, которое создает 50 потоков один за
//    одним, каждый из потоков выводит сообщение "Hello from thread
//    (number of thread)", особенность заключается в том, что вывод
//    должен быть строго в обратном порядке, от 49 до 0