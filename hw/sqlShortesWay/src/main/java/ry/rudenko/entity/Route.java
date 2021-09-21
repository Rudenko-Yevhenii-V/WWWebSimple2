package ry.rudenko.entity;

  public record Route (
      Integer id,
      Integer from_id,
      Integer to_id,
      Integer cost

  ) implements BaseEntity{

    public Route(Integer id, Integer from_id, Integer to_id, Integer cost) {
      this.id = id;
      this.from_id = from_id;
      this.to_id = to_id;
      this.cost = cost;
    }
  }
