package fr.alcidauk.school.schedule.business;

import fr.alcidauk.school.schedule.beans.ActivityTime;
import fr.alcidauk.school.schedule.beans.Domain;
import fr.alcidauk.school.schedule.beans.EmptyTime;
import fr.alcidauk.school.schedule.beans.WeekSchedule;

import java.util.*;

public class ActivityTimesGenerator {

    private List<Domain> randomDomains = new ArrayList<>();

    public void generateActivityTimes(WeekSchedule weekSchedule) {
        populateRandomDomains(weekSchedule);

        Map<Domain, Integer> numberOfActivitiesPerDomain = weekSchedule.getNumberOfActivitiesPerDomain();

        Domain previousDomain = null;
        for (EmptyTime emptyTime : weekSchedule.getEmptyTimes()) {
            Domain domainOfRandom = getRandomDomain(previousDomain);
            previousDomain = domainOfRandom;

            weekSchedule.getActivityTimes().add(
                    new ActivityTime(
                            domainOfRandom,
                            emptyTime.getDayOfWeek(),
                            emptyTime.getStartMinute(),
                            emptyTime.getEndMinute()
                    )
            );
            numberOfActivitiesPerDomain.put(domainOfRandom, numberOfActivitiesPerDomain.get(domainOfRandom) - 1);

            cleanActivitiesPerDomain(numberOfActivitiesPerDomain);
        }

    }

    private void populateRandomDomains(WeekSchedule weekSchedule) {
        for (Domain domain : weekSchedule.getNumberOfActivitiesPerDomain().keySet()) {
            for(int i=0; i < weekSchedule.getNumberOfActivitiesPerDomain().get(domain); i++){
                randomDomains.add(domain);
            }
        }

        Collections.shuffle(randomDomains);
    }

    private Domain getRandomDomain(Domain previousDomain) {
        Random random = new Random();
        int randomDomainIndex = random.nextInt(randomDomains.size());

        Domain domainToReturn = randomDomains.get(randomDomainIndex);

        if(previousDomain == null || !previousDomain.equals(domainToReturn)) {
            randomDomains.remove(randomDomainIndex);

            return domainToReturn;
        } else {
            return getRandomDomain(previousDomain);
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
