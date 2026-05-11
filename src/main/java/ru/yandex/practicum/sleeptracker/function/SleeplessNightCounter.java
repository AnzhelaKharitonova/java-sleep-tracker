package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightCounter implements Function<List<SleepingSession>, SleepAnalysisResult<Integer>> {
    private final String DESCRIPTION = "Количество бессонных ночей";

    @Override
    public SleepAnalysisResult<Integer> apply(List<SleepingSession> sleepingSessions) {
        LocalDate startDate;
        LocalDate endDate = sleepingSessions.get(sleepingSessions.size() - 1).getWakeUpTime().toLocalDate();
        LocalDateTime bedTimeFirstSession = sleepingSessions.get(0).getBedTime();
        if (bedTimeFirstSession.isBefore(
                LocalDateTime.of(bedTimeFirstSession.toLocalDate(), LocalTime.NOON))) {
            startDate = bedTimeFirstSession.toLocalDate().minusDays(1);
        } else {
            startDate = bedTimeFirstSession.toLocalDate();
        }

        long amountOfNightInPeriod = ChronoUnit.DAYS.between(startDate, endDate);
        long count = sleepingSessions.stream()
                .filter(session -> session.isNighttimeSleepSession())
                .count();
        long result = amountOfNightInPeriod - count;

        return new SleepAnalysisResult<>((int) result, DESCRIPTION);
    }
}
