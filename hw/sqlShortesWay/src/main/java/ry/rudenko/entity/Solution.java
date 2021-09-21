package ry.rudenko.entity;

  public record Solution (
      Integer problem_id,
      Integer cost
  ) implements BaseEntity{

    public Solution(Integer problem_id, Integer cost) {
      this.problem_id = problem_id;
      this.cost = cost;
    }
  }
