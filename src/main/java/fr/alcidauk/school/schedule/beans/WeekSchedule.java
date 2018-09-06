package fr.alcidauk.school.schedule.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeekSchedule {

    private List<EmptyTime> emptyTimes;
    private List<ActivityTime> activityTimes;

    private Map<Domain, Integer> numberOfActivitiesPerDomain;

    public WeekSchedule() {
        this.emptyTimes = new ArrayList<>();
        this.activityTimes = new ArrayList<>();
        this.numberOfActivitiesPerDomain = new HashMap<>();
    }

    public void setEmptyTimes(List<EmptyTime> emptyTimes) {
        this.emptyTimes = emptyTimes;
    }

    public List<EmptyTime> getEmptyTimes() {
        return emptyTimes;
    }

    public List<ActivityTime> getActivityTimes() {
        return activityTimes;
    }

    public void setActivityTimes(List<ActivityTime> activityTimes) {
        this.activityTimes = activityTimes;
    }

    public void addActivityNumberForDomain(Domain domain, int numberOfActivities) {
        this.numberOfActivitiesPerDomain.put(domain, numberOfActivities);
    }

    @Override
    public String toString() {
        return "WeekSchedule{" +
                "emptyTimes=" + emptyTimes +
                ", activityTimes=" + activityTimes +
                '}';
    }

}
