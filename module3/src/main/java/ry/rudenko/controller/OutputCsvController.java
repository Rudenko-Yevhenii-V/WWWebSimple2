package ry.rudenko.controller;


import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.model.dto.CsvData;
import ry.rudenko.util.BuildHibernateSessionFactory;

public class OutputCsvController {

  private final String path = "outputOperationHistory.csv";
  private static final Logger log = LoggerFactory.getLogger(OutputCsvController.class);
  final Session session = BuildHibernateSessionFactory.buildSessionFactory().openSession();
  private final Executor executor = Executors.newFixedThreadPool(1);

  public void createCsv(UUID idAccount, String start, String end) {
    session.doWork(connection -> {
      connection.setNetworkTimeout(
          executor,
          (int) TimeUnit.SECONDS.toMillis(1)
      );

      try (PreparedStatement getToCsvData = connection.prepareStatement(
          "SELECT o.amount, o.date, o.type, c.action_type FROM operations  as o "
              + "LEFT JOIN category as c On o.category_id = c.category_id WHERE account_id = ? AND date between ? AND ?",
          PreparedStatement.RETURN_GENERATED_KEYS
      )) {
        getToCsvData.setObject(1, idAccount);

        getToCsvData.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .parse(start).getTime()));
        getToCsvData.setTimestamp(3, new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .parse(end).getTime()));
        final ResultSet generatedKeys = getToCsvData.executeQuery();
        List<CsvData> list = new ArrayList<>();
        File fileToOutputCsv = new File(path);
        if (!fileToOutputCsv.exists()) {
          final boolean newFile = fileToOutputCsv.createNewFile();
          log.info("Is created file for writing data to csv? - {} ",newFile);
        }
        while (generatedKeys.next()) {
          CsvData csvData = CsvData.builder()
              .amount(new BigInteger(String.valueOf(generatedKeys.getLong("amount"))))
              .date(generatedKeys.getTimestamp("date").toInstant())
              .type(generatedKeys.getString("type"))
              .actionType(generatedKeys.getString("action_type"))
              .build();
          System.out.println("csvData = " + csvData);
          log.info("csv data = {}", csvData);
          list.add(csvData);
        }
        connection.commit();
        List<String[]> csv = new ArrayList<>();

        String[] header = new String[4];
        header[0] = "amount";
        header[1] = "date";
        header[2] = "type";
        header[3] = "action_type";
        csv.add(header);
        for (CsvData csvData : list) {
          String[] row = new String[4];
          row[0] = String.valueOf(csvData.getAmount());
          row[1] = String.valueOf(csvData.getDate());
          row[2] = csvData.getType();
          row[3] = csvData.getActionType();
          csv.add(row);
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
          log.info("Try to write data to csv{}",csv);
          writer.writeAll(csv);

        }
      } catch (SQLException | ParseException | IOException e) {
        connection.rollback();
        log.error("Don't created output ", e);
        throw new RuntimeException(e);
      }
    });
  }
}
