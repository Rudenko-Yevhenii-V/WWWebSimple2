package ry.rudenko.repository;


import ry.rudenko.model.entity.User;

public interface UserRepository {
   User findByPhone(String phone);
   User save(User user);
}
