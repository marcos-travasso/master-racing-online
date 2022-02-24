package online.masterracing.model;

import javax.persistence.*;
import java.time.LocalTime;
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
    private LocalTime startTime;

    @ManyToOne(cascade = CascadeType.ALL)
    private Circuit circuit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
    private Set<Participation> participants = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Participation> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participation> participants) {
        this.participants = participants;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
