package ry.rudenko.model.facade.impl;


import java.math.BigInteger;
import java.time.Instant;
import ry.rudenko.model.dto.CsvData;
import ry.rudenko.model.facade.CsvDataFacade;

public class CsvDataFacadeImpl implements CsvDataFacade {

  @Override
  public CsvData register(Instant date, BigInteger amount, String type, String actionType) {
    CsvData csvData = new CsvData();
    csvData.setDate(date);
    csvData.setAmount(amount);
    csvData.setType(type);
    csvData.setActionType(actionType);
    return csvData;
  }
}
