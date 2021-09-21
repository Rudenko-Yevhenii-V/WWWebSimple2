package ry.rudenko.entity;

public record Location (
    Integer id,
    String name
) implements BaseEntity{

  public Location(Integer id, String name) {
    this.id = id;
    this.name = name;
  }
}
