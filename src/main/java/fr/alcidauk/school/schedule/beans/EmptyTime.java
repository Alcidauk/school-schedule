package fr.alcidauk.school.schedule.beans;

import java.util.Objects;

public class EmptyTime {

    private int dayOfWeek;
    private int startMinute;
    private int endMinute;

    public EmptyTime(int dayOfWeek, int startMinute, int endMinute) {
        this.dayOfWeek = dayOfWeek;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmptyTime emptyTime = (EmptyTime) o;
        return dayOfWeek == emptyTime.dayOfWeek &&
                startMinute == emptyTime.startMinute &&
                endMinute == emptyTime.endMinute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, startMinute, endMinute);
    }

    @Override
    public String toString() {
        return "EmptyTime{" +
                "dayOfWeek=" + dayOfWeek +
                ", startMinute=" + startMinute +
                ", endMinute=" + endMinute +
                '}';
    }
}
