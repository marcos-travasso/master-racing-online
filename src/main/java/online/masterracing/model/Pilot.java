package online.masterracing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pilots")
public class Pilot extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "pilot")
    @JsonIgnore
    private final Set<Participation> participation = new HashSet<>();

    public Pilot(String name) {
        this.name = name;
    }

    public Pilot() {

    }

    public String getName() {
        return name;
    }

    public Set<Participation> getParticipation() {
        return participation;
    }
}
