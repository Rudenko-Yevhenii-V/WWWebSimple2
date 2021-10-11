package ry.rudenko.service;


import java.math.BigInteger;
import ry.rudenko.model.entity.Operation;

public interface OperationService {
  Operation findById(Long id);
  Operation addOperation(String phone, String type, BigInteger amount);

}