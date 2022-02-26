package online.masterracing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import online.masterracing.exceptions.RaceAlreadyStartedException;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    public void setParticipants(Set<Participation> participants) {
        this.participants = participants;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    @JsonIgnore
    public Long getRaceTime(){
        long maxTime = 0L;

        for(Participation participation : getParticipants()){
            long sum = participation.getLaps().stream().mapToLong(Lap::getTimeElapsed).sum();
            if (sum > maxTime){
                maxTime = sum;
            }
        }

        return maxTime;
    }

    public RaceStats getStats(){
        List<Lap> allLaps = new ArrayList<>();
        getParticipants().forEach(participation -> allLaps.addAll(participation.getLaps()));

        RaceStats stats = new RaceStats(allLaps);
        stats.setTotalTime(getRaceTime());

        return stats;
    }

    @JsonIgnore
    public Optional<Pilot> getWinner(){
        if(getStartTime() == null){
            throw new NotStartedRaceException();
        }

        Participation bestParticipation = getMaxTimeParticipation();
        boolean found = false;

        for(Participation participation : getParticipants()){
            if(participation.getLaps().size() == getLaps()){
                long participationDuration = participation.getStats().getTotalTimeDuration().toMillis();
                long bestDuration = bestParticipation.getStats().getTotalTimeDuration().toMillis();

                if(participationDuration < bestDuration){
                    bestParticipation = participation;
                    found = true;
                }
            }
        }

        if(!found){
            return Optional.empty();
        }

        return Optional.of(bestParticipation.getPilot());
    }

    @JsonIgnore
    private Participation getMaxTimeParticipation(){
        Participation participation = new Participation(new Pilot(), new Race());
        participation.setLaps(new ArrayList<>());

        Lap dummyLap = new Lap();
        dummyLap.setTimeElapsed(Long.MAX_VALUE);

        participation.getLaps().add(dummyLap);

        return participation;
    }
}
