package ry.rudenko.util;

import java.util.Map;

public record CSVTable(Map<String, String[]> rows,
                       String[] header) {

  public Map<String, String[]> getRows() {
    return rows;
  }

  public String[] getHeader() {
    return header;
  }
}
