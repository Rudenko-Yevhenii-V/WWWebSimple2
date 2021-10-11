package ry.rudenko.service;


import java.math.BigInteger;
import java.util.UUID;
import ry.rudenko.model.entity.Operation;

public interface OperationService {

  Operation findById(UUID id);

  Operation addOperation(UUID idAccount, String type, BigInteger amount, Boolean income,
      String actionType);

}