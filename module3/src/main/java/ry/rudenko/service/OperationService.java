package ry.rudenko.service;


import java.math.BigInteger;
import ry.rudenko.model.entity.Operation;

public interface OperationService {
  Operation findById(Long id);
  Operation addOperation(Long idAccount, String type, BigInteger amount,Boolean income, String actionType);

}