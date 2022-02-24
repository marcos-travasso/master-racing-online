package online.masterracing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import online.masterracing.exceptions.NotStartedRaceException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "participation")
public class Participation extends BaseEntity {
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "race_id")
    @JsonIgnore
    private Race race;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participation")
    private List<Lap> laps = new ArrayList<>();

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
        pilot.getParticipation().add(this);
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
        race.getParticipants().add(this);
    }

    public List<Lap> getLaps() {
        return laps;
    }

    public void addLap() throws NotStartedRaceException {
        if(getRace().getStartTime() == null){
            throw new NotStartedRaceException();
        }

        Lap lap = new Lap(this);
        laps.add(lap);
    }
}
