package com.example.employees.service;


import com.example.employees.model.Pairs;
import com.example.employees.model.RecordEntry;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ProjectService {
    public static Map<Integer, List<RecordEntry>> groupByProject(List<RecordEntry> entries) {

        Map<Integer, List<RecordEntry>> projectsMap = new HashMap<>();
        for (RecordEntry entry : entries) {
            int projectId = entry.projectId();
            List<RecordEntry> list = projectsMap.get(projectId);
            if (list == null) {
                list = new ArrayList<>();
                projectsMap.put(projectId, list);
            }
            list.add(entry);
        }
        return projectsMap;
    }


    public static List<Pairs> getPairsPerProject(int projectId, List<RecordEntry> entriesForProject) {
        List<Pairs> pairsMap = new ArrayList<>();
        for (int i = 0; i < entriesForProject.size(); i++) {
            for (int j = i + 1; j < entriesForProject.size(); j++) {

                RecordEntry e1 = entriesForProject.get(i);
                RecordEntry e2 = entriesForProject.get(j);

                if (e1.empId() == e2.empId()) {
                    continue;
                }

                LocalDate start;
                LocalDate end;

                if (e1.dateFrom().isAfter(e2.dateFrom())) {
                    start = e1.dateFrom();
                } else {
                    start = e2.dateFrom();
                }

                if (e1.dateTo().isBefore(e2.dateTo())) {
                    end = e1.dateTo();
                } else {
                    end = e2.dateTo();
                }

                if (end.isBefore(start)) {
                    continue;
                }

                long daysBetween = ChronoUnit.DAYS.between(start, end);

//                int empA = Math.min(e1.empId(), e2.empId());
//                int empB = Math.max(e1.empId(), e2.empId());
//                String pairKey = empA + "-" + empB;

                Pairs pair = new Pairs(new Pairs.PairKey(Set.of(e1.empId(), e2.empId())), projectId, daysBetween);
                pairsMap.add(pair);
            }
        }

        return pairsMap;
    }

    public static Map.Entry<Pairs.PairKey, Long> getLongestWorkingPair(List<Pairs> allPairs) {

        Map<Pairs.PairKey, Long> totalDaysPerPair = new HashMap<>();

        for (Pairs pair : allPairs) {
            long currentTotalDays = totalDaysPerPair.getOrDefault(pair.getPairKey(), 0L);
            totalDaysPerPair.put(pair.getPairKey(), currentTotalDays + pair.getDaysWorked());
        }

        Map.Entry<Pairs.PairKey, Long> max = null;
        for (Map.Entry<Pairs.PairKey, Long> entry : totalDaysPerPair.entrySet()) {
            long next = entry.getValue();
            if (max == null || next > max.getValue()) {
                max = entry;
            }
        }
        return max;
    }
}


