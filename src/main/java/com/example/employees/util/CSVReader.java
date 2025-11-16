//package com.example.employees.util;
//
//import com.example.employees.model.RecordEntry;
//
//import java.io.*;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class CSVReader {
//    public static List<RecordEntry> parse(InputStream inputStream) {
//        List<RecordEntry> entries = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                int empId = Integer.parseInt(parts[0].trim());
//                int projectId = Integer.parseInt(parts[1].trim());
//                LocalDate from = LocalDate.parse(parts[2].trim());
//                LocalDate to = parts[3].trim().equals("NULL") ? LocalDate.now() : LocalDate.parse(parts[3].trim());
//
//                RecordEntry entry = new RecordEntry(empId, projectId, from, to);
//                entries.add(entry);
//            }
//        } catch (IOException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//        }
//
//        return entries;
//    }
//}

