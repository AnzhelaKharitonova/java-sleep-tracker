package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.ChronotypeAnalyzer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChronotypeAnalyzerTest {
    @Test
    void resultShouldBeOwlType() throws InvalidDataInLogException {
        ChronotypeAnalyzer function = new ChronotypeAnalyzer();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 23:05;06.10.25 09:20;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("07.10.25 01:10;07.10.25 10:00;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("07.10.25 21:00;08.10.25 05:00;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);

        assertEquals(Chronotype.OWL, function.apply(sessions).getResult());
    }

    @Test
    void resultShouldBeLarkType() throws InvalidDataInLogException {
        ChronotypeAnalyzer function = new ChronotypeAnalyzer();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 23:05;06.10.25 06:20;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("07.10.25 21:00;08.10.25 06:00;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("08.10.25 20:00;09.10.25 06:40;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);

        assertEquals(Chronotype.LARK, function.apply(sessions).getResult());
    }

    @Test
    void resultShouldBePigeonType() throws InvalidDataInLogException {
        ChronotypeAnalyzer function = new ChronotypeAnalyzer();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 23:05;06.10.25 09:20;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("07.10.25 21:00;08.10.25 06:00;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("08.10.25 23:30;09.10.25 06:40;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);

        assertEquals(Chronotype.PIGEON, function.apply(sessions).getResult());
    }

    @Test
    void resultShouldBePigeonTypeIfTwoEqualType() throws InvalidDataInLogException {
        ChronotypeAnalyzer function = new ChronotypeAnalyzer();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 23:05;06.10.25 09:20;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("07.10.25 23:30;08.10.25 10:00;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("08.10.25 21:30;09.10.25 06:40;GOOD");
        SleepingSession session4 = SleepingSession.parseSessionFromLine("08.10.25 20:30;09.10.25 05:40;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);

        assertEquals(Chronotype.PIGEON, function.apply(sessions).getResult());
    }
}
