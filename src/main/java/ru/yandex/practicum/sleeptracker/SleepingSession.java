package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SleepingSession {
    private final LocalDateTime bedTime;
    private final LocalDateTime wakeUpTime;
    private final SleepQuality sleepQuality;


    private SleepingSession(LocalDateTime bedTime, LocalDateTime wakeUpTime, SleepQuality sleepQuality) {
        this.bedTime = bedTime;
        this.wakeUpTime = wakeUpTime;
        this.sleepQuality = sleepQuality;
    }

    public static SleepingSession parseSessionFromLine(String line) throws InvalidDataInLogException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        LocalDateTime bedTime;
        LocalDateTime wakeUpTime;
        SleepQuality sleepQuality;
        try {
            String[] split = line.split(";");
            if (split.length != 3) {
                throw new InvalidDataInLogException("Количество сегментов в строке через разделитель ';' не равно 3");
            }
            try {
                bedTime = LocalDateTime.parse(split[0], formatter);
                wakeUpTime = LocalDateTime.parse(split[1], formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidDataInLogException("Неверный формат даты в строке");
            }
            if (bedTime.isAfter(wakeUpTime)) {
                throw new InvalidDataInLogException("Дата и время начала сна в строке позже чем дата и время окончания сна");
            }
            sleepQuality = switch (split[2]) {
                case "BAD" -> SleepQuality.BAD;
                case "NORMAL" -> SleepQuality.NORMAL;
                case "GOOD" -> SleepQuality.GOOD;
                default ->
                        throw new InvalidDataInLogException("Ошибка при чтении характеристики качества сна из файла");
            };
            return new SleepingSession(bedTime, wakeUpTime, sleepQuality);
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDateTime getBedTime() {
        return bedTime;
    }

    public LocalDateTime getWakeUpTime() {
        return wakeUpTime;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }

    public boolean isNighttimeSleepSession() {
        LocalDateTime startOfNight = LocalDateTime.of(bedTime.toLocalDate(), LocalTime.MIDNIGHT);
        LocalDateTime endOfNight = LocalDateTime.of(bedTime.toLocalDate(), LocalTime.of(06, 00));

        if (bedTime.toLocalDate().isBefore(wakeUpTime.toLocalDate())) {
            return true;
        } else if (bedTime.isBefore(endOfNight) && startOfNight.isBefore(wakeUpTime)) {
            return true;
        } else {
            return false;
        }
    }

    public Chronotype getChronotype() {
        if (bedTime.toLocalDate().isEqual(wakeUpTime.toLocalDate())
                && bedTime.toLocalTime().isAfter(LocalTime.of(9, 0))
                && wakeUpTime.toLocalTime().isBefore(LocalTime.of(21, 0))) {
            return null;
        }
        if ((bedTime.toLocalTime().isAfter(LocalTime.of(23, 0))
                || bedTime.toLocalTime().isBefore(LocalTime.of(9, 0)))
                && wakeUpTime.toLocalTime().isAfter(LocalTime.of(9, 0))) {
            return Chronotype.OWL;
        }
        if (bedTime.toLocalTime().isBefore(LocalTime.of(22, 0))
                && wakeUpTime.toLocalTime().isBefore(LocalTime.of(7, 0))) {
            return Chronotype.LARK;
        }
        return Chronotype.PIGEON;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "bedTime=" + bedTime +
                ", wakeUpTime=" + wakeUpTime +
                ", sleepQuality=" + sleepQuality +
                '}';
    }
}
