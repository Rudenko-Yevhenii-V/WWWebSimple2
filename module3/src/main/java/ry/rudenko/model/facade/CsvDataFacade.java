package ry.rudenko.model.facade;


import java.math.BigInteger;
import java.time.Instant;
import ry.rudenko.model.dto.CsvData;

public interface CsvDataFacade {
  CsvData register(Instant date, BigInteger amount, String type, String actionType);
}
