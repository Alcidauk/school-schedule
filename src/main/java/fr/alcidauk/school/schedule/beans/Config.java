package fr.alcidauk.school.schedule.beans;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private List<AvailableTime> availableTimes;
    private List<Domain> domains;

    public Config() {
        this.availableTimes = new ArrayList<>();
        this.domains = new ArrayList<>();
    }

    public void addAvailableTime(AvailableTime availableTime) {
        availableTimes.add(availableTime);
    }

    public void addDomain(Domain domain) {
        domains.add(domain);
    }
}
