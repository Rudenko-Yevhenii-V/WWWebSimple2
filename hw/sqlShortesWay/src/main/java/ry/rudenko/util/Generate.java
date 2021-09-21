package ry.rudenko.util;


import java.util.UUID;

public class Generate {
  public int getGenerateId() {
    return (int) (UUID.randomUUID().getMostSignificantBits() & Integer.MAX_VALUE/10);
  }
}
