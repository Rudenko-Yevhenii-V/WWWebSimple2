package ry.rudenko.entity;

public record Problem (
    Integer id,
    Integer from_id,
    Integer to_id
) implements BaseEntity{

  public Problem(Integer id, Integer from_id, Integer to_id) {
    this.id = id;
    this.from_id = from_id;
    this.to_id = to_id;
  }
}
