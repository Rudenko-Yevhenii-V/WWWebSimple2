package ry.rudenko.yevhenii.service;


import ry.rudenko.yevhenii.entity.Student;

public interface StudentService {
  Student findById(Long id);
}
