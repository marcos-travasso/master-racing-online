package online.masterracing.model;

import java.util.ArrayList;
import java.util.List;

public class ParticipationDTO extends BaseEntityDTO {
    private PilotDTO pilot;

    private RaceDTO race;

    private List<Lap> laps = new ArrayList<>();

    public PilotDTO getPilot() {
        return pilot;
    }

    public void setPilot(PilotDTO pilot) {
        this.pilot = pilot;
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(RaceDTO race) {
        this.race = race;
    }

    public List<Lap> getLaps() {
        return laps;
    }

    public void setLaps(List<Lap> laps) {
        this.laps = laps;
    }
}
