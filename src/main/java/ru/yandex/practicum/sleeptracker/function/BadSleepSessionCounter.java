package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class BadSleepSessionCounter implements Function<List<SleepingSession>, SleepAnalysisResult<Integer>> {
    private final String DESCRIPTION = "Количество сессий с плохим качеством сна";

    @Override
    public SleepAnalysisResult<Integer> apply(List<SleepingSession> sleepingSessions) {
        long result = sleepingSessions.stream()
                .filter(session ->
                        session.getSleepQuality() == SleepQuality.BAD)
                .count();
        return new SleepAnalysisResult<>((int) result, DESCRIPTION);
    }
}