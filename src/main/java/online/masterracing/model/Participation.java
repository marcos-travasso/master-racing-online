package online.masterracing.model;

import javax.persistence.*;

@Entity
@Table(name = "participation")
public class Participation extends BaseEntity {
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "race_id")
    private Race race;

    //TODO laps
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "participation")
    //private Set<Lap> laps = new HashSet<>();

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
}
