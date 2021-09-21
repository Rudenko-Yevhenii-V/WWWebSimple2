package ry.rudenko.entity;

public record Problem (
    Integer id,
    Integer from_id,
    Integer to_id
) implements BaseEntity{

}
