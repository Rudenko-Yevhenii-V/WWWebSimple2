package ry.rudenko.task;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class Two {

  public static Integer numberOfNubmbers = 100;
  public static volatile List numbers;
  public static volatile Iterator iterator;

  public static void main(String[] args) {
    numbers = Collections.synchronizedList(new ArrayList());
    Random random = new Random();
    for (int i = 0; i < numberOfNubmbers; i++) {
//      numbers.add(random.nextInt(999));
      numbers.add(i);
      System.out.println("Number is : " + numbers.get(i));
    }
    iterator = numbers.iterator();
    SecondTaskThread callable1 = new SecondTaskThread(iterator);
    SecondTaskThread callable2 = new SecondTaskThread(iterator);
    FutureTask<Integer> futureTask1 = new FutureTask<>(callable1);
    FutureTask<Integer> futureTask2 = new FutureTask<>(callable2);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(futureTask1);
    executor.execute(futureTask2);

    try {
      Integer result1 = futureTask1.get();
      Integer result2 = futureTask2.get();
      System.out.println("result1 = " + result1);
      System.out.println("result2 = " + result2);
      System.out.printf("List have got %d prime numbers", (result1 + result2));
//      System.exit(0);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  public synchronized Integer getPrimeOfNubmbers(Iterator iterator) {
    final Integer next = (Integer) iterator.next();
    if (!new Two().isPrime(next)) {
      return next;
    }
    return null;
  }

  boolean isPrime(int number) {
    return IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
  }
}

class SecondTaskThread implements Callable<Integer> {

  Iterator iterator;

  public SecondTaskThread(Iterator iterator) {
    this.iterator = iterator;
  }

  @Override
  public Integer call() {

    int count = 0;
    while (iterator.hasNext()) {
      final Integer primeOfNubmbers = new Two().getPrimeOfNubmbers(iterator);
      if (!(primeOfNubmbers == null)&&(primeOfNubmbers!=0)) {
        System.out.println(
            "number = " + primeOfNubmbers + " - " + Thread.currentThread().getName());
        count++;
      }
    }
    return count;
  }

}

//  Напишите приложение, которое в 2 потока будет считать
//    количество простых чисел, которые заданы в List, выводить
//    результат и возвращать его в главный поток.
//    Главный поток подсчитывает и выводит общее количество.