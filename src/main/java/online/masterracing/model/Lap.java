package online.masterracing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Duration;
import java.time.Instant;

@Entity
@Table(name = "laps")
public class Lap extends BaseEntity{

    @Column(name = "time_elapsed")
    private Long timeElapsed;

    @Column(name = "actual_time")
    private Instant actualTime;

    @ManyToOne
    @JsonIgnore
    private Participation participation;

    public Lap(Participation participation) {
        this.participation = participation;
        this.actualTime = Instant.now();
        this.timeElapsed =  Duration.between(participation.getLastInstant(), Instant.now()).toMillis();
    }

    public Lap() {
    }

    public Long getTimeElapsed() {
        return timeElapsed;
    }

    public Instant getActualTime() {
        return actualTime;
    }

    public Participation getParticipation() {
        return participation;
    }
}
