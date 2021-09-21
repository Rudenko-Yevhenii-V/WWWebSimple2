package ry.rudenko.entity;

  public record Route (
      Integer id,
      Integer from_id,
      Integer to_id,
      Integer cost

  ) implements BaseEntity{

  }
