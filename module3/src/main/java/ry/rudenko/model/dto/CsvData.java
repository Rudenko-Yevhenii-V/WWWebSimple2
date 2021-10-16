package ry.rudenko.model.dto;


import java.math.BigInteger;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;


@Data
public class CsvData {
  private Instant date;
  private BigInteger amount;
  private String type;
  private String actionType;

  public CsvData() {
  }
}
