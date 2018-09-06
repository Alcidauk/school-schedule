package fr.alcidauk.school.schedule.business;

public class TimeConversion {

    public int convert(String hourAsString) {
        String[] splittedHour = hourAsString.split(":");

        String hours = splittedHour[0];
        String minutes = splittedHour[1];

        return Integer.parseInt(hours) * 60 + Integer.parseInt(minutes);
    }

    public String convert(int hourAsInt) {
        if(hourAsInt > 1439){
            throw new NumberFormatException("Can't convert minutes that corresponds at more than one day.");
        }
        int hours = hourAsInt / 60;
        return String.format("%02d:%02d", hours, hourAsInt - hours * 60);
    }
}
