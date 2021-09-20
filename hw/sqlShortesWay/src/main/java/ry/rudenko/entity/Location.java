package ry.rudenko.entity;

  public record Location(
      String phoneNumber,
      String name,
      String description,
      boolean active
  ) {

    public Location(String phoneNumber, String name, String description) {
      this(phoneNumber, name, description, true);
    }

  }
