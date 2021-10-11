package ry.rudenko.repository;


import java.util.UUID;
import ry.rudenko.model.entity.Operation;

public interface OperationRepository {
  Operation findById(UUID id);
  Operation addOperation(Operation operation);
}
