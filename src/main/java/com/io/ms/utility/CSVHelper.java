package com.io.ms.utility;

import com.io.ms.entities.school.SchoolNameTutorial;
import org.springframework.web.multipart.MultipartFile;
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

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "country", "state", "city", "name" ,"board", "contactNum1","contactNum2", "email", "linkdinID",
            "address1", "pincode", "websiteURL"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<SchoolNameTutorial> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<SchoolNameTutorial> tutorials = new ArrayList<SchoolNameTutorial>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                SchoolNameTutorial tutorial = new SchoolNameTutorial(
                        csvRecord.get("country"),
                        csvRecord.get("state"),
                        csvRecord.get("city"),
                        csvRecord.get("name"),
                        csvRecord.get("board"),
                        csvRecord.get("contactNum1"),
                        csvRecord.get("contactNum2"),
                        csvRecord.get("email"),
                        csvRecord.get("linkdinID"),
                        csvRecord.get("address1"),
                        csvRecord.get("pincode"),
                        csvRecord.get("websiteURL")
                );

                tutorials.add(tutorial);
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
