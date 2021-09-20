package ry.rudenko;


import ry.rudenko.entity.Location;

public class Main {

  public static void main(String[] args) {
    System.out.println("Main.main");
   Location location =  new Location("10438530325", "Jane Doe", null, false);
    System.out.println("location.name() = " + location.name());
  }

}
