package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.TotalSleepingSessions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TotalSleepingSessionsTest {

    @Test
    void resultShouldBe4Sessions() throws InvalidDataInLogException {
        TotalSleepingSessions function = new TotalSleepingSessions();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 00:10;05.10.25 06:20;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("06.10.25 00:10;05.10.25 07:40;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("10.10.25 13:00;10.10.25 14:30;NORMAL");
        SleepingSession session4 = SleepingSession.parseSessionFromLine("11.10.25 23:10;12.10.25 07:00;BAD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);

        assertEquals(4, function.apply(sessions).getResult());
    }

    @Test
    void resultShouldBe0Sessions() throws InvalidDataInLogException {
        TotalSleepingSessions function = new TotalSleepingSessions();
        List<SleepingSession> sessions = new ArrayList<>();

        assertEquals(0, function.apply(sessions).getResult());
    }
}