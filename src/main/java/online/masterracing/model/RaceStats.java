package online.masterracing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import online.masterracing.exceptions.NegativeTimeElapsedException;

import java.time.Duration;
import java.util.List;

public class RaceStats {
    private Long totalTime;
    private final Long averageTime;
    private final Long minimumTime;
    private final Long maximumTime;

    public RaceStats(List<Lap> laps) {
        verifyLaps(laps);

        totalTime = laps.stream().mapToLong(Lap::getTimeElapsed).sum();
        minimumTime = laps.stream().mapToLong(Lap::getTimeElapsed).min().orElse(0);
        maximumTime = laps.stream().mapToLong(Lap::getTimeElapsed).max().orElse(0);
        if(laps.isEmpty()){
            averageTime = 0L;
        } else {
            averageTime = totalTime/laps.size();
        }
    }

    public String readable(Duration duration){
        return duration.toString().substring(2).toLowerCase();
    }

    public String getTotalTime() {
        return readable(Duration.ofMillis(totalTime));
    }

    public String getAverageTime() {
        return readable(Duration.ofMillis(averageTime));
    }

    public String getMinimumTime() {
        return readable(Duration.ofMillis(minimumTime));
    }

    public String getMaximumTime() {
        return readable(Duration.ofMillis(maximumTime));
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    @JsonIgnore
    public Duration getTotalTimeDuration(){
        return Duration.ofMillis(totalTime);
    }

    @Override
    public String toString() {
        return "Total time: " + getTotalTime() + "\n" +
                "Average time: " + getAverageTime() + "\n" +
                "Minimum time: " + getMinimumTime() + "\n" +
                "Maximum time: " + getMaximumTime();
    }

    private void verifyLaps(List<Lap> laps) throws NegativeTimeElapsedException {
        for(Lap lap: laps){
            if(lap.getTimeElapsed() < 0){
                throw new NegativeTimeElapsedException();
            }
        }
    }
}
