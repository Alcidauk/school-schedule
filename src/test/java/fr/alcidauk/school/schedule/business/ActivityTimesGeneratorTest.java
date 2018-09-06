package fr.alcidauk.school.schedule.business;

import fr.alcidauk.school.schedule.beans.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityTimesGeneratorTest {

    @Test
    void generateActivityTimes() {
        WeekSchedule weekSchedule = new WeekSchedule();
        weekSchedule.setEmptyTimes(
                new ArrayList<>() {{
                    add(new EmptyTime(0, 555, 585));
                    add(new EmptyTime(0, 615, 645));
                    add(new EmptyTime(0, 645, 675));

                    add(new EmptyTime(1, 555, 585));
                    add(new EmptyTime(1, 615, 645));
                    add(new EmptyTime(1, 645, 675));

                    add(new EmptyTime(2, 555, 585));
                    add(new EmptyTime(2, 615, 645));
                    add(new EmptyTime(2, 645, 675));

                    add(new EmptyTime(3, 555, 585));
                    add(new EmptyTime(3, 615, 645));
                    add(new EmptyTime(3, 645, 675));
                }}
        );

        Domain agir = new Domain("Agir", 1);
        Domain manger = new Domain("Manger", 2);

        weekSchedule.addActivityNumberForDomain(agir, 4);
        weekSchedule.addActivityNumberForDomain(manger, 8);

        Config config = new Config();
        config.addDomain(agir);
        config.addDomain(manger);

        new ActivityTimesGenerator().generateActivityTimes(
                weekSchedule
        );

        List<ActivityTime> activityTimes = weekSchedule.getActivityTimes();

        assertEquals(12, activityTimes.size());

        assertEquals(
                4,
                activityTimes.stream()
                        .filter(activityTime -> activityTime.getDomain().equals(agir))
                        .collect(toList())
                        .size()
        );
        assertEquals(
                8,
                activityTimes.stream()
                        .filter(activityTime -> activityTime.getDomain().equals(manger))
                        .collect(toList())
                        .size()
        );

    }
}