package fr.alcidauk.school.schedule.beans;

public class ActivityTime {

    private Domain domain;
    private int dayInWeek;
    private int startMinute;
    private int endMinute;

    public ActivityTime(Domain domain, int dayInWeek, int startMinute, int endMinute) {
        this.domain = domain;
        this.dayInWeek = dayInWeek;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
    }

    public Domain getDomain() {
        return domain;
    }

    public int getDayInWeek() {
        return dayInWeek;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getEndMinute() {
        return endMinute;
    }
}
