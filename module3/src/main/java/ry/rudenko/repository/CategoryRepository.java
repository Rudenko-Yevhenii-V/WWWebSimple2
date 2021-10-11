package ry.rudenko.repository;


import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Category;

public interface CategoryRepository {
  Category save(Category category);
}
