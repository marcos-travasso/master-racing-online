package online.masterracing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pilots")
public class Pilot extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "pilot")
    @JsonIgnore
    private final Set<Participation> participation = new HashSet<>();

    public Pilot() {

    }

    public Pilot(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Participation> getParticipation() {
        return participation;
    }

    public boolean isInRace(Race race){
        for(Participation p : getParticipation()){
            if(Objects.equals(p.getRace().getId(), race.getId())){
                return true;
            }
        }

        return false;
    }
}
