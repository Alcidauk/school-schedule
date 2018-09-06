package fr.alcidauk.school.schedule.business;

import fr.alcidauk.school.schedule.beans.ActivityTime;
import fr.alcidauk.school.schedule.beans.Domain;
import fr.alcidauk.school.schedule.beans.EmptyTime;
import fr.alcidauk.school.schedule.beans.WeekSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ActivityTimesGenerator {
    public void generateActivityTimes(WeekSchedule weekSchedule) {
        Map<Domain, Integer> numberOfActivitiesPerDomain = weekSchedule.getNumberOfActivitiesPerDomain();

        for (EmptyTime emptyTime : weekSchedule.getEmptyTimes()) {
            Random random = new Random();
            int randomDomain = random.nextInt(numberOfActivitiesPerDomain.keySet().size());

            int i = 0;
            for (Domain domain : numberOfActivitiesPerDomain.keySet()) {
                if (i == randomDomain) {
                    weekSchedule.getActivityTimes().add(
                            new ActivityTime(
                                    domain,
                                    emptyTime.getDayOfWeek(),
                                    emptyTime.getStartMinute(),
                                    emptyTime.getEndMinute()
                            )
                    );

                    numberOfActivitiesPerDomain.put(domain, numberOfActivitiesPerDomain.get(domain) - 1);
                }

                i++;
            }

            cleanActivitiesPerDomain(numberOfActivitiesPerDomain);
        }

    }

    private void cleanActivitiesPerDomain(Map<Domain, Integer> numberOfActivitiesPerDomain) {
        List<Domain> domainsToRemove = new ArrayList<>();
        numberOfActivitiesPerDomain.forEach((domain, integer) -> {
            if (integer == 0) domainsToRemove.add(domain);
        });

        for (Domain domain : domainsToRemove) {
            numberOfActivitiesPerDomain.remove(domain);
        }
    }
}
