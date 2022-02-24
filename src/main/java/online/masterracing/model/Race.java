package online.masterracing.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import online.masterracing.exceptions.RaceAlreadyStartedException;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "laps")
    private int laps;

    @Column(name = "start_time")
    private Instant startTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Circuit circuit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
    private Set<Participation> participants = new HashSet<>();

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
        circuit.getRaces().add(this);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Participation> getParticipants() {
        return participants;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public void startRace(){
        if(getStartTime() != null){
            throw new RaceAlreadyStartedException();
        }

        setStartTime(Instant.now());
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
