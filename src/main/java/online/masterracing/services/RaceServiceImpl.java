package online.masterracing.services;

import online.masterracing.exceptions.RaceNotFoundException;
import online.masterracing.model.Lap;
import online.masterracing.model.Race;
import online.masterracing.model.RaceStats;
import online.masterracing.repositories.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RaceServiceImpl implements RaceService{

    private final RaceRepository raceRepository;

    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public Set<Race> findAll() {
        Set<Race> races = new HashSet<>();
        raceRepository.findAll().forEach(races::add);
        return races;
    }

    @Override
    public Race findById(Long aLong){
        return raceRepository.findById(aLong).orElseThrow(RaceNotFoundException::new);
    }

    @Override
    public Race save(Race object) {
        return raceRepository.save(object);
    }

    @Override
    public void delete(Race object) {
        raceRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        raceRepository.deleteById(aLong);
    }

    @Override
    public void startRace(Long id) {
        Race race = findById(id);

        race.startRace();
        save(race);
    }

    @Override
    public RaceStats getStats(Long id) throws RaceNotFoundException {
        Race race = findById(id);
        List<Lap> allLaps = new ArrayList<>();
        race.getParticipants().forEach(participation -> allLaps.addAll(participation.getLaps()));

        return new RaceStats(allLaps);
    }
}
