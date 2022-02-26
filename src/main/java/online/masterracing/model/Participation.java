package online.masterracing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.exceptions.PilotFinishedRaceException;

import javax.persistence.*;
import java.time.Instant;
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

    public Participation(Pilot pilot, Race race) {
        setPilot(pilot);
        setRace(race);
    }

    public Participation() {

    }

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

    public void setLaps(List<Lap> laps) {
        this.laps = laps;
    }

    public void addLap() throws NotStartedRaceException {
        if(getRace().getStartTime() == null){
            throw new NotStartedRaceException();
        }

        if(getRace().getLaps() == getLaps().size()){
            throw new PilotFinishedRaceException();
        }

        Lap lap = new Lap(this);
        laps.add(lap);
    }

    @JsonIgnore
    public Instant getLastInstant(){
        if(getLaps().isEmpty()){
            return getRace().getStartTime();
        }

        Lap lastLap = getLaps().get(getLaps().size() - 1);
        return lastLap.getActualTime();
    }

    public RaceStats getStats(){
        return new RaceStats(getLaps());
    }
}
