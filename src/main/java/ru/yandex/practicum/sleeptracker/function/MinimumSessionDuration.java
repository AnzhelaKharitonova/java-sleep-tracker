package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.OptionalLong;
import java.util.function.Function;

public class MinimumSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult<Integer>> {

    @Override
    public SleepAnalysisResult<Integer> apply(List<SleepingSession> sleepingSessions) {
        OptionalLong result = sleepingSessions.stream()
                .map(session ->
                        Duration.between(session.getBedTime(), session.getWakeUpTime()).toMinutes())
                .mapToLong(Long::longValue)
                .min();
        if (result.isPresent()) {
            return new SleepAnalysisResult<>((int) result.getAsLong(),
                    "Минимальная продолжительность сессии (в минутах)");
        }
        return null;
    }
}
