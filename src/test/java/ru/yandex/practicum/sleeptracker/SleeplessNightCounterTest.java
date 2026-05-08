package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.SleeplessNightCounter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SleeplessNightCounterTest {

    @Test
    void resultShouldBe2Nights() throws InvalidDataInLogException {
        SleeplessNightCounter function = new SleeplessNightCounter();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("05.10.25 23:05;06.10.25 06:20;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("05.10.25 13:10;05.10.25 13:20;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("07.10.25 01:00;07.10.25 08:00;GOOD");
        SleepingSession session4 = SleepingSession.parseSessionFromLine("07.10.25 14:00;07.10.25 14:40;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);

        assertEquals(0, function.apply(sessions).getResult());
    }

    @Test
    void resultShouldBe0IfOneNightSession() throws InvalidDataInLogException {
        SleeplessNightCounter function = new SleeplessNightCounter();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("01.10.25 00:05;01.10.25 06:10;GOOD");

        sessions.add(session1);

        assertEquals(0, function.apply(sessions).getResult());
    }
    @Test
    void resultShouldBe1IfOneDaySession() throws InvalidDataInLogException {
        SleeplessNightCounter function = new SleeplessNightCounter();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("01.10.25 07:05;01.10.25 14:10;GOOD");

        sessions.add(session1);

        assertEquals(1, function.apply(sessions).getResult());
    }
    @Test
    void resultShouldBe2IfTwoNightSession() throws InvalidDataInLogException {
        SleeplessNightCounter function = new SleeplessNightCounter();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("01.10.25 03:50;01.10.25 04:10;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("01.10.25 23:05;02.10.25 06:00;GOOD");

        sessions.add(session1);
        sessions.add(session2);

        assertEquals(0, function.apply(sessions).getResult());
    }
    @Test
    void resultShouldBe30IfNotNightSession() throws InvalidDataInLogException {
        SleeplessNightCounter function = new SleeplessNightCounter();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("01.10.25 15:50;01.10.25 16:50;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("31.10.25 23:05;31.10.25 23:55;GOOD");

        sessions.add(session1);
        sessions.add(session2);

        assertEquals(30, function.apply(sessions).getResult());
    }
    @Test
    void resultShouldBe1IfTimeFirstSessionBeforeNoon() throws InvalidDataInLogException {
        SleeplessNightCounter function = new SleeplessNightCounter();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("02.10.25 11:50;02.10.25 16:50;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("02.10.25 23:05;03.10.25 09:00;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("03.10.25 21:00;04.10.25 07:00;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);

        assertEquals(1, function.apply(sessions).getResult());
    }

    @Test
    void resultShouldBe3() throws InvalidDataInLogException {
        SleeplessNightCounter function = new SleeplessNightCounter();
        List<SleepingSession> sessions = new ArrayList<>();
        SleepingSession session1 = SleepingSession.parseSessionFromLine("30.09.25 15:50;30.09.25 16:50;GOOD");
        SleepingSession session2 = SleepingSession.parseSessionFromLine("01.10.25 23:05;02.10.25 06:55;GOOD");
        SleepingSession session3 = SleepingSession.parseSessionFromLine("02.10.25 21:50;02.10.25 23:50;GOOD");
        SleepingSession session4 = SleepingSession.parseSessionFromLine("03.10.25 15:50;03.10.25 21:50;GOOD");
        SleepingSession session5 = SleepingSession.parseSessionFromLine("04.10.25 12:50;04.10.25 13:50;GOOD");

        sessions.add(session1);
        sessions.add(session2);
        sessions.add(session3);
        sessions.add(session4);
        sessions.add(session5);

        assertEquals(3, function.apply(sessions).getResult());
    }

}