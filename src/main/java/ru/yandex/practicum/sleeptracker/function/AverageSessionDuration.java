package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AverageSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult<Integer>> {

    @Override
    public SleepAnalysisResult<Integer> apply(List<SleepingSession> sleepingSessions) {
        OptionalDouble result = sleepingSessions.stream()
                .map(session ->
                        Duration.between(session.getBedTime(), session.getWakeUpTime()).toMinutes())
                .mapToLong(Long::longValue)
                .average();
        if (result.isPresent()) {
            return new SleepAnalysisResult<>((int) result.getAsDouble(),
                    "Средняя продолжительность сессии (в минутах)");
        }
        return null;
    }
}

