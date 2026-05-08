package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.AverageSessionDuration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AverageSessionDurationTest {

    @Test
    void resultShouldBe270Minutes() throws InvalidDataInLogException {
        AverageSessionDuration function = new AverageSessionDuration();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 00:00;05.10.25 06:00;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("10.10.25 12:00;10.10.25 15:00;GOOD");

        sessions.add(session1);
        sessions.add(session2);

        assertEquals(270, function.apply(sessions).getResult());
    }

    @Test
    void resultShouldBe120Minutes() throws InvalidDataInLogException {
        AverageSessionDuration function = new AverageSessionDuration();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 00:00;05.10.25 02:00;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("05.10.25 13:10;05.10.25 15:10;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("10.10.25 21:10;10.10.25 23:10;GOOD");
        SleepingSession session4 = SleepingSession.parseSessionFromLine("11.10.25 14:00;11.10.25 16:00;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);

        assertEquals(120, function.apply(sessions).getResult());
    }

}