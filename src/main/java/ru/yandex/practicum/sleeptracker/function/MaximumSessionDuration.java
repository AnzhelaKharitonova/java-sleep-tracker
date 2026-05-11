package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.OptionalLong;
import java.util.function.Function;

public class MaximumSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult<Integer>> {
    private final String DESCRIPTION = "Максимальная продолжительность сессии (в минутах)";

    @Override
    public SleepAnalysisResult<Integer> apply(List<SleepingSession> sleepingSessions) {
        OptionalLong result = sleepingSessions.stream()
                .map(session ->
                        Duration.between(session.getBedTime(), session.getWakeUpTime()).toMinutes())
                .mapToLong(Long::longValue)
                .max();
        if (result.isPresent()) {
            return new SleepAnalysisResult<>((int) result.getAsLong(), DESCRIPTION);
        }
        return null;
    }
}
