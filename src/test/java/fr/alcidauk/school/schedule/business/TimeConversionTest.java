package fr.alcidauk.school.schedule.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeConversionTest {

    @Test
    void convertStringHourToIntMidnight() {
        assertEquals(0, new TimeConversion().convert("00:00"));
    }

    @Test
    void convertStringHourToIntMidday() {
        assertEquals(720, new TimeConversion().convert("12:00"));
    }

    @Test
    void convertStringHourToIntSomeHour() {
        assertEquals(1122, new TimeConversion().convert("18:42"));
    }

    @Test
    void convertStringHourToIntBadPattern() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            new TimeConversion().convert("18h42");
        });
    }

    @Test
    void convertIntHourToStringMidnight() {
        assertEquals("00:00", new TimeConversion().convert(0));
    }

    @Test
    void convertIntHourToStringMidday() {
        assertEquals("12:00", new TimeConversion().convert(720));
    }

    @Test
    void convertIntHourToStringSomeHour() {
        assertEquals("18:42", new TimeConversion().convert(1122));
    }

    @Test
    void convertIntHourToStringBadNumber() {
        assertThrows(NumberFormatException.class, () -> {
            new TimeConversion().convert(1441);
        });
    }
}