package com.io.ms.utility;

import java.time.format.DateTimeFormatter;


public class GlobalUtility {

    public static String generateSerialNumber() {
        // Implement your logic to generate a unique 8-digit serial number here
        // Example: Generate a random 8-digit number
        return String.format("%08d", (int) (Math.random() * 100000000));
    }

    public static DateTimeFormatter generateDateFormat1() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter;
    }

    public static DateTimeFormatter generateDateFormat2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return formatter;
    }

}
