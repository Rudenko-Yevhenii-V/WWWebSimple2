package ry.rudenko.task;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class Two {
  public static  Integer numberOfNubmbers = 1000;
  public static void main(String[] args) {
    List<Integer> numbers = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < numberOfNubmbers; i++) {
      numbers.add(random.nextInt(999));
      System.out.println("Number is : " + numbers.get(i));
    }
    synchronized (numberOfNubmbers) {
      SecondTaskThread callable1 = new SecondTaskThread(numbers);
      SecondTaskThread callable2 = new SecondTaskThread(numbers);
      FutureTask futureTask1 = new FutureTask(callable1);
      FutureTask futureTask2 = new FutureTask(callable2);
      ExecutorService executor = Executors.newFixedThreadPool(2);
      executor.execute(futureTask1);
      executor.execute(futureTask2);

      try {
        Integer result1 = (Integer) futureTask1.get();
        Integer result2 = (Integer) futureTask2.get();
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        System.exit(0);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    }
  public synchronized Integer decrement(List<Integer> numbers) {
    System.out.println(numberOfNubmbers + "  !!!!!!!!!!!!!!!!!!!!!");
    numberOfNubmbers--;
    if(!isPrime(numbers.get(Two.numberOfNubmbers))&&numbers.get(Two.numberOfNubmbers)!=0){
      return numbers.get(numberOfNubmbers);
    }
    return null;
  }
  boolean isPrime(int number) {

    return IntStream.rangeClosed(2, number / 2).anyMatch(i -> number % i == 0);
  }
}
class SecondTaskThread implements Callable<Integer> {
  List<Integer> numbers;

  public SecondTaskThread(List<Integer> numbers) {
    this.numbers = Collections.synchronizedList(numbers);
  }

  @Override
  public Integer call() {
    int count = 0;
    while (Two.numberOfNubmbers > 0){
      final Integer decrement = new Two().decrement(numbers);
      if(!(decrement == null)){
        System.out.println("number = " + decrement + " - " + Thread.currentThread().getName());
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