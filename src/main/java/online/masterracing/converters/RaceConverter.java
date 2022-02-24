package online.masterracing.converters;

import online.masterracing.model.Circuit;
import online.masterracing.model.Race;
import online.masterracing.model.RaceDTO;

public class RaceConverter {
    private RaceConverter(){}

    public static Race convertToRace(RaceDTO raceDTO){
        Race race = new Race();
        race.setId(raceDTO.getId());
        race.setDescription(raceDTO.getDescription());
        race.setLaps(raceDTO.getLaps());
        race.setStartTime(raceDTO.getStartTime());
        race.setCategory(raceDTO.getCategory());
        race.setCircuit(new Circuit());
        race.getCircuit().setId(raceDTO.getCircuitId());

        return race;
    }

    public static RaceDTO convertToDTO(Race race){
        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setId(race.getId());
        raceDTO.setDescription(race.getDescription());
        raceDTO.setLaps(race.getLaps());
        raceDTO.setStartTime(race.getStartTime());
        raceDTO.setCategory(race.getCategory());
        raceDTO.setCircuitId(race.getCircuit().getId());

        return raceDTO;
    }
}
