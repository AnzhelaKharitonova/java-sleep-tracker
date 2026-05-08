package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SleepingSessionTest {

    @Test
    void isNighttimeSleepSessionTestReturnFalse() throws InvalidDataInLogException {
        SleepingSession falseSleepingSession1 = SleepingSession.parseSessionFromLine("01.01.25 13:00;01.01.25 14:30;NORMAL");
        SleepingSession falseSleepingSession2 = SleepingSession.parseSessionFromLine("05.01.25 21:00;05.01.25 23:59;NORMAL");
        SleepingSession falseSleepingSession3 = SleepingSession.parseSessionFromLine("06.01.25 06:01;06.01.25 10:30;NORMAL");

        assertFalse(falseSleepingSession1.isNighttimeSleepSession());
        assertFalse(falseSleepingSession2.isNighttimeSleepSession());
        assertFalse(falseSleepingSession3.isNighttimeSleepSession());
    }

    @Test
    void isNighttimeSleepSessionTestReturnTrue() throws InvalidDataInLogException {
        SleepingSession trueSleepingSession1 = SleepingSession.parseSessionFromLine("01.01.25 00:00;01.01.25 00:10;NORMAL");
        SleepingSession trueSleepingSession2 = SleepingSession.parseSessionFromLine("05.01.25 21:00;06.01.25 09:59;NORMAL");
        SleepingSession trueSleepingSession3 = SleepingSession.parseSessionFromLine("07.01.25 05:59;07.01.25 10:30;NORMAL");
        SleepingSession trueSleepingSession4 = SleepingSession.parseSessionFromLine("08.01.25 22:59;09.01.25 00:01;NORMAL");

        assertTrue(trueSleepingSession1.isNighttimeSleepSession());
        assertTrue(trueSleepingSession2.isNighttimeSleepSession());
        assertTrue(trueSleepingSession3.isNighttimeSleepSession());
        assertTrue(trueSleepingSession4.isNighttimeSleepSession());
    }

    @Test
    void returnChronotypeOwl() throws InvalidDataInLogException {
        SleepingSession sleepingSession1 = SleepingSession.parseSessionFromLine("01.01.25 01:00;01.01.25 09:01;NORMAL");
        SleepingSession sleepingSession2 = SleepingSession.parseSessionFromLine("05.01.25 23:01;06.01.25 09:59;NORMAL");

        assertEquals(Chronotype.OWL, sleepingSession1.getChronotype());
        assertEquals(Chronotype.OWL, sleepingSession2.getChronotype());
    }

    @Test
    void returnChronotypeLark() throws InvalidDataInLogException {
        SleepingSession sleepingSession1 = SleepingSession.parseSessionFromLine("01.01.25 21:59;02.01.25 06:01;NORMAL");
        SleepingSession sleepingSession2 = SleepingSession.parseSessionFromLine("05.01.25 20:01;06.01.25 06:59;NORMAL");

        assertEquals(Chronotype.LARK, sleepingSession1.getChronotype());
        assertEquals(Chronotype.LARK, sleepingSession2.getChronotype());
    }
    @Test
    void returnChronotypePigeon() throws InvalidDataInLogException {
        SleepingSession sleepingSession1 = SleepingSession.parseSessionFromLine("01.01.25 01:00;01.01.25 08:59;NORMAL");
        SleepingSession sleepingSession2 = SleepingSession.parseSessionFromLine("05.01.25 21:01;06.01.25 07:30;NORMAL");

        assertEquals(Chronotype.PIGEON, sleepingSession1.getChronotype());
        assertEquals(Chronotype.PIGEON, sleepingSession2.getChronotype());

    }

    @Test
    void returnChronotypeNull() throws InvalidDataInLogException {
        SleepingSession sleepingSession1 = SleepingSession.parseSessionFromLine("01.01.25 10:00;01.01.25 11:00;NORMAL");
        SleepingSession sleepingSession2 = SleepingSession.parseSessionFromLine("05.01.25 13:01;05.01.25 15:30;NORMAL");

        assertNull(sleepingSession1.getChronotype());
        assertNull(sleepingSession2.getChronotype());

    }
}