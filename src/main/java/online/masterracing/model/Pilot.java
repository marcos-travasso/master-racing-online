package online.masterracing.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pilots")
public class Pilot extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "pilot")
    private Set<Participation> participation = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Participation> getParticipation() {
        return participation;
    }

    public void setParticipation(Set<Participation> participation) {
        this.participation = participation;
    }
}
