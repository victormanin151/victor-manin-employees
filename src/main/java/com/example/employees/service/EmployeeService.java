package com.example.employees.service;

import com.example.employees.model.Pairs;
import com.example.employees.model.RecordEntry;
//import com.example.employees.util.CSVReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private List<RecordEntry> entries = new ArrayList<>();

    public void processCsv(MultipartFile file, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            List<RecordEntry> parsedEntries = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty()){
                    continue;
                }
                String[] parts = line.split(",");
                int empId = Integer.parseInt(parts[0].trim());
                int projectId = Integer.parseInt(parts[1].trim());

                LocalDate from = LocalDate.parse(parts[2].trim(), formatter);
                LocalDate to = parts[3].trim().equals("NULL")
                        ? LocalDate.now()
                        : LocalDate.parse(parts[3].trim(), formatter);

                parsedEntries.add(new RecordEntry(empId, projectId, from, to));
            }

            this.entries = parsedEntries;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV", e);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Date parsing failed. Make sure the format matches your selection.", e);
        }
    }

    public List<RecordEntry> getEntries() {
        return entries;
    }

    public Map.Entry<Pairs.PairKey, Long> calculateLongestWorkingPair() {

        Map<Integer, List<RecordEntry>> grouped = ProjectService.groupByProject(entries);

        List<Pairs> allPairs = new ArrayList<>();

        for (Map.Entry<Integer, List<RecordEntry>> entry : grouped.entrySet()) {
            allPairs.addAll(ProjectService.getPairsPerProject(entry.getKey(), entry.getValue()));
        }

        return ProjectService.getLongestWorkingPair(allPairs);
    }
}
