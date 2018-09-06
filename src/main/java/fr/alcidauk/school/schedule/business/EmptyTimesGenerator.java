package fr.alcidauk.school.schedule.business;

import fr.alcidauk.school.schedule.beans.AvailableTime;
import fr.alcidauk.school.schedule.beans.Config;
import fr.alcidauk.school.schedule.beans.EmptyTime;
import fr.alcidauk.school.schedule.beans.WeekSchedule;

public class EmptyTimesGenerator {

    public void generateEmptyTimes(Config config, WeekSchedule weekSchedule) {
        int dayOfWeek = 0;
        while (dayOfWeek < 4) {
            generateEmptyTimeForDay(config, weekSchedule, dayOfWeek);
            dayOfWeek++;
        }

    }

    private void generateEmptyTimeForDay(Config config, WeekSchedule weekSchedule, int dayOfWeek) {
        for (AvailableTime availableTime : config.getAvailableTimes()) {
            int start = availableTime.getStart();
            int end = availableTime.getEnd();

            int currentStart = start;
            while (currentStart + 5 < end) {
                weekSchedule.getEmptyTimes().add(new EmptyTime(dayOfWeek, currentStart, currentStart + 30));
                currentStart += 30;
            }
        }
    }
}
