package ry.rudenko.repository;


import ry.rudenko.model.entity.Operation;

public interface OperationRepository {
  Operation findById(Long id);
  Operation addOperation(Operation operation);
}
