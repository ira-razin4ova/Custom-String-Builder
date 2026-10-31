package org.example.stream_api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkSheet {
    String name;
    List<DayOfWeek> workDays;

    public WorkSheet(String name, List<DayOfWeek> workDays) {
        this.name = name;
        this.workDays = workDays;
    }

    public String getName() {
        return name;
    }

    public List<DayOfWeek> getWorkDays() {
        return workDays;
    }

   public void test() {
        var denSheet = new WorkSheet("Денис", List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY));
        var benSheet = new WorkSheet("Бен", List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY));
        var lisSheet = new WorkSheet("Лиза", List.of(DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        var sheets = List.of(denSheet, benSheet, lisSheet);
        sheets.stream()
                .flatMap(sheet -> sheet.workDays.stream())
                .collect(Collectors.groupingBy(DayOfWeek::name, Collectors.counting()))
                .entrySet().stream()
                .filter(day -> day.getValue() >= 2)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
    }
}
