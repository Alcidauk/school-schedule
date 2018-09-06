package fr.alcidauk.school.schedule.business;

import fr.alcidauk.school.schedule.beans.AvailableTime;
import fr.alcidauk.school.schedule.beans.Config;
import fr.alcidauk.school.schedule.beans.EmptyTime;
import fr.alcidauk.school.schedule.beans.WeekSchedule;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmptyTimesGeneratorTest {


    @Test
    void generateEmptyTimes() {
        WeekSchedule weekSchedule = new WeekSchedule();
        Config config = new Config();
        config.addAvailableTime(new AvailableTime(555, 585));
        config.addAvailableTime(new AvailableTime(615, 680));

        new EmptyTimesGenerator().generateEmptyTimes(config, weekSchedule);
        assertEquals(
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
                }},
                weekSchedule.getEmptyTimes()
        );
    }
}