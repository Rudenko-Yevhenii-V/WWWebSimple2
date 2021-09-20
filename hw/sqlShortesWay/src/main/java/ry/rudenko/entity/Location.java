package ry.rudenko.entity;

public record Location (
    String id,
    String name
) implements BaseEntity{

  public Location(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
