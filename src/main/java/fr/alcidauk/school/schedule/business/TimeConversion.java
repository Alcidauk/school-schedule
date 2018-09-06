package fr.alcidauk.school.schedule.business;

public class TimeConversion {
    public int convert(String hourAsString) {
        String[] splittedHour = hourAsString.split(":");

        String hours = splittedHour[0];
        String minutes = splittedHour[1];

        return Integer.parseInt(hours) * 60 + Integer.parseInt(minutes);
    }
}
