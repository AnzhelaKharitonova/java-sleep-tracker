package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.MaximumSessionDuration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaximumSessionDurationTest {

    @Test
    void resultShouldBe540Minutes() throws InvalidDataInLogException {
        MaximumSessionDuration function = new MaximumSessionDuration();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 00:05;05.10.25 06:20;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("05.10.25 13:10;05.10.25 13:20;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("09.10.25 23:00;10.10.25 08:00;GOOD");
        SleepingSession session4 = SleepingSession.parseSessionFromLine("11.10.25 14:00;11.10.25 14:40;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);

        assertEquals(540, function.apply(sessions).getResult());
    }

    @Test
    void resultShouldBe60Minutes() throws InvalidDataInLogException {
        MaximumSessionDuration function = new MaximumSessionDuration();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 00:05;05.10.25 00:10;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("05.10.25 13:10;05.10.25 14:10;GOOD");

        sessions.add(session1);
        sessions.add(session2);

        assertEquals(60, function.apply(sessions).getResult());
    }

}