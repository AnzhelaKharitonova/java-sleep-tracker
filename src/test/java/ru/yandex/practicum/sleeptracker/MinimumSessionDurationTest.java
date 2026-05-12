package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.MinimumSessionDuration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinimumSessionDurationTest {

    @Test
    void resultShouldBe10Minutes() throws InvalidDataInLogException {
        MinimumSessionDuration function = new MinimumSessionDuration();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 00:05;05.10.25 06:20;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("05.10.25 13:10;05.10.25 13:20;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("10.10.25 21:10;11.10.25 05:20;GOOD");
        SleepingSession session4 = SleepingSession.parseSessionFromLine("11.10.25 14:00;11.10.25 14:40;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);

        assertEquals(10, function.apply(sessions).getResult());
    }

    @Test
    void resultShouldBe70Minutes() throws InvalidDataInLogException {
        MinimumSessionDuration function = new MinimumSessionDuration();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 00:05;05.10.25 06:20;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("05.10.25 13:10;05.10.25 16:20;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("10.10.25 21:10;11.10.25 05:20;GOOD");
        SleepingSession session4 = SleepingSession.parseSessionFromLine("11.10.25 14:00;11.10.25 15:10;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);

        assertEquals(70, function.apply(sessions).getResult());
    }

}