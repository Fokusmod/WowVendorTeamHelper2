package ru.geekbrains.WowVendorTeamHelper.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.geekbrains.WowVendorTeamHelper.exeptions.AppError;
import ru.geekbrains.WowVendorTeamHelper.exeptions.ResourceNotFoundException;
import ru.geekbrains.WowVendorTeamHelper.exeptions.TeamNotFoundException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class DateService {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE dd MMM @ HH:mm ", Locale.ENGLISH);
    private final DateTimeFormatter scheduleDate = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.getDefault());
    private final DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.M.yy", Locale.ENGLISH);
    private final DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
    private final ZoneId mskZone = ZoneId.of("Europe/Moscow");
    private final ZoneId cetZone = ZoneId.of("CET");

    public boolean checkDateFormat(String text) {

        try {
            LocalDate.parse(text,formatDate);
        } catch (DateTimeException exception){
            System.out.println("Date");
            System.out.println(exception);
           return false;
        }
        return true;
    }
    public boolean checkTimeFormat(String text) {
        try {
            LocalTime.parse(text,formatTime);
        } catch (DateTimeException exception){
            System.out.println(exception);
            System.out.println("Time");
            return false;
        }
        return true;
    }

    public List<String> getTimeMscAndCET() {
//      формат  (Sat 28 Jan @ 21:00 CET)
        List<String> timeList = new ArrayList<>();
        String mskTime = LocalDateTime.now(mskZone).format(dateTimeFormatter) + "MSC";
        String cetTime = LocalDateTime.now(cetZone).format(dateTimeFormatter) + "CET";
        timeList.add(mskTime);
        timeList.add(cetTime);
        return timeList;
    }

    public List<String> getCurrentWeek() {
        String currentDate;
        List<String> currentDayWeek = new ArrayList<>();
        LocalDate previousTuesday = LocalDate.now(mskZone).plusDays(1).with(TemporalAdjusters.previous(DayOfWeek.TUESDAY));
        for (int i = 0; i < 7; i++) {
            currentDate = previousTuesday.plusDays(i).format(scheduleDate);
            currentDayWeek.add(currentDate);

        }
        return currentDayWeek;
    }

    public List<String> getNextWeek(int count) {
        String weekDay;
        List<String> nextDayWeek = new ArrayList<>();
        LocalDate previousTuesday = LocalDate.now(mskZone).with(TemporalAdjusters.previous(DayOfWeek.TUESDAY));
        LocalDate mondayOnNextWeek = previousTuesday.plusWeeks(count).with(DayOfWeek.TUESDAY);
        for (int i = 0; i < 7; i++) {
            weekDay = mondayOnNextWeek.plusDays(i).format(scheduleDate);
            nextDayWeek.add(weekDay);
        }
        return nextDayWeek;
    }

    public List<String> getPrevWeek(int count) {
        String weekDay;
        List<String> prevDayWeek = new ArrayList<>();
        LocalDate previousTuesday = LocalDate.now(mskZone).with(TemporalAdjusters.previous(DayOfWeek.TUESDAY));
        LocalDate mondayOnPreviousWeek = previousTuesday.minusWeeks(count).with(DayOfWeek.TUESDAY);
        for (int i = 0; i < 7; i++) {
            weekDay = mondayOnPreviousWeek.plusDays(i).format(scheduleDate);
            prevDayWeek.add(weekDay);
        }
        return prevDayWeek;
    }

}
