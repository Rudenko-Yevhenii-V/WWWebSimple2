package ry.rudenko.util;


import java.util.UUID;

public class Generate {

  public String getGenerateId() {
    final UUID uuid = UUID.randomUUID();
    return String.valueOf(uuid);
  }

}
