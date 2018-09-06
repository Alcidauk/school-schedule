package fr.alcidauk.school.schedule.beans;

public class AvailableTime {

    private int start;
    private int end;

    public AvailableTime(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
