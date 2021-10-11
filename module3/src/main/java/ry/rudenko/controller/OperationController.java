package ry.rudenko.controller;


import java.math.BigInteger;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.Main;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.IncomeCategory;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.repository.impl.CategoryRepositoryImpl;
import ry.rudenko.repository.impl.OperationRepositoryImpl;
import ry.rudenko.service.impl.OperationServiceImpl;
import ry.rudenko.util.BuildHibernateSessionFactory;

public class OperationController {
  private static final Logger log = LoggerFactory.getLogger(OperationController.class);
  final Session session = BuildHibernateSessionFactory.buildSessionFactory().openSession();

  public void createOperation(String phone){
    final Operation operation;
    try {
      operation = new OperationServiceImpl(
          new OperationRepositoryImpl(session)).addOperation(
              phone
          ,"mandatory spending"
          ,new BigInteger("222222")
      );





      System.out.println("operation.getType() = " + operation.getType());
    } catch (EmptySessionException e) {
      log.error("Session did not transferred! ", e);
      throw new RuntimeException(e);
    }

  }

}
