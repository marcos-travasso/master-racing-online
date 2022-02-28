package online.masterracing.model;

import online.masterracing.exceptions.NegativeTimeElapsedException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RaceStatsTest {

    @Test
    public void Test_ValidLapStats(){
        List<Lap> laps = new ArrayList<>();
        Lap lap = new Lap();
        lap.setTimeElapsed(1L);

        laps.add(lap);

        RaceStats raceStats = new RaceStats(laps);

        assertNotNull(raceStats.getTotalTimeDuration());
    }

    @Test
    public void Test_NegativeTimeElapsedStats(){
        List<Lap> laps = new ArrayList<>();
        Lap lap = new Lap();
        lap.setTimeElapsed(-1L);

        laps.add(lap);

        assertThrows(NegativeTimeElapsedException.class, () -> {
            RaceStats raceStats = new RaceStats(laps);
        });
    }

    @Test
    public void Test_GetTotalTime(){
        List<Lap> laps = new ArrayList<>();

        Lap lap1 = new Lap();
        lap1.setTimeElapsed(1L);

        Lap lap2 = new Lap();
        lap2.setTimeElapsed(2L);

        laps.add(lap1);
        laps.add(lap2);

        RaceStats raceStats = new RaceStats(laps);
        Long totalTime = raceStats.getTotalTimeDuration().toMillis();

        assertEquals(3L, totalTime);
    }
}
