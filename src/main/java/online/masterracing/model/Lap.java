package online.masterracing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "laps")
public class Lap extends BaseEntity{

    @Column(name = "time_elapsed")
    private LocalTime timeElapsed;

    @ManyToOne
    private Participation participation;

    public LocalTime getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(LocalTime timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public Participation getParticipation() {
        return participation;
    }

    public void setParticipation(Participation participation) {
        this.participation = participation;
    }
}
