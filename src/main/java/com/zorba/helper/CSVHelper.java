package com.zorba.helper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.zorba.entity.Users;

public class CSVHelper {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id","Name", "Email", "Contact", "Role" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Users> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Users> userLists = new ArrayList<Users>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        Users userList = new Users(
        		Integer.parseInt(csvRecord.get("Id")), 
        		csvRecord.get("Name"), 
        		csvRecord.get("Email"), 
        		csvRecord.get("Contact"), 
        		csvRecord.get("Role")
        	);

        userLists.add(userList);
      }

      return userLists;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

}
