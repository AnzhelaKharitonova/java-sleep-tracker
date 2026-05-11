package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.*;
import java.util.function.Function;

public class ChronotypeAnalyzer implements Function<List<SleepingSession>, SleepAnalysisResult<Chronotype>> {
    private static final String DESCRIPTION = "Ваш хронотип";

    @Override
    public SleepAnalysisResult<Chronotype> apply(List<SleepingSession> sleepingSessions) {
        Chronotype result;
        long amountOwlType = sleepingSessions.stream()
                .filter(session -> session.getChronotype() == Chronotype.OWL)
                .count();
        long amountLarkType = sleepingSessions.stream()
                .filter(session -> session.getChronotype() == Chronotype.LARK)
                .count();
        long amountPigeonType = sleepingSessions.stream()
                .filter(session -> session.getChronotype() == Chronotype.PIGEON)
                .count();

        if (amountOwlType > amountLarkType && amountOwlType > amountPigeonType) {
            result = Chronotype.OWL;
        } else if (amountLarkType > amountOwlType && amountLarkType > amountPigeonType) {
            result = Chronotype.LARK;
        } else {
            result = Chronotype.PIGEON;
        }
        return new SleepAnalysisResult<>(result, DESCRIPTION);
    }
}
