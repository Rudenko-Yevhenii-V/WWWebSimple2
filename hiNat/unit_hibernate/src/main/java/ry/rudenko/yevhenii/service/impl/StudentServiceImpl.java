package ry.rudenko.yevhenii.service.impl;


import ry.rudenko.yevhenii.entity.Student;
import ry.rudenko.yevhenii.repository.StudentRepository;
import ry.rudenko.yevhenii.service.StudentService;

public class StudentServiceImpl implements StudentService {
private final StudentRepository studentRepository;

  public StudentServiceImpl(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  @Override
  public Student findById(Long id) {
    return studentRepository.findById(id);
  }

  @Override
  public Student findByPhone(Long Phone) {
    return studentRepository.findByPhone(Phone);
  }
}
