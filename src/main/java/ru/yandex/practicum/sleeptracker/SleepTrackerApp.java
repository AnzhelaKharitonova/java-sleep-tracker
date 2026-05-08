package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.function.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class SleepTrackerApp {
    private static final String FILE_NAME = "sleep_log.txt";

    public static void main(String[] args) {
        List<SleepingSession> sleepingSessions = loadSleepingSessions(FILE_NAME);
        ArrayList<Function<List<SleepingSession>, ?>> functions = new ArrayList<>();

        functions.add(new TotalSleepingSessions());
        functions.add(new MinimumSessionDuration());
        functions.add(new MaximumSessionDuration());
        functions.add(new AverageSessionDuration());
        functions.add(new BadSleepSessionCounter());
        functions.add(new SleeplessNightCounter());
        functions.add(new ChronotypeAnalyzer());

        functions.stream()
                .map(t -> t.apply(sleepingSessions))
                .forEach(System.out::println);
    }

    private static List<SleepingSession> loadSleepingSessions(String fileName)  {
        List<SleepingSession> sleepingSessions;

        try (InputStream is = SleepTrackerApp.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new IllegalArgumentException("Файл не найден: " + fileName);
            }
            Stream<String> lines = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines();
            sleepingSessions = lines.filter(line -> !line.isEmpty())
                    .map(String::trim)
                    .map(line -> {
                        try {
                            return SleepingSession.parseSessionFromLine(line);
                        } catch (InvalidDataInLogException e) {
                            System.out.println(e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
            return sleepingSessions;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
