package online.masterracing.services;

import online.masterracing.exceptions.NotFoundException;
import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.model.Pilot;
import online.masterracing.model.Race;
import online.masterracing.model.RaceStats;
import online.masterracing.repositories.RaceRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RaceServiceImpl implements RaceService{

    private final RaceRepository raceRepository;
    private final CircuitService circuitService;

    public RaceServiceImpl(RaceRepository raceRepository, CircuitService circuitService) {
        this.raceRepository = raceRepository;
        this.circuitService = circuitService;
    }

    @Override
    public Set<Race> findAll() {
        return new HashSet<>(raceRepository.findAll());
    }

    @Override
    public Race findById(Long aLong){
        return raceRepository.findById(aLong).orElseThrow(NotFoundException::new);
    }

    @Override
    public Race save(Race object) {
        object.setCircuit(circuitService.findById(object.getCircuit().getId()));
        return raceRepository.save(object);
    }

    @Override
    public void startRace(Long id) {
        Race race = findById(id);

        race.startRace();
        save(race);
    }

    @Override
    public RaceStats getStats(Long id) {
        Race race = findById(id);

        return race.getStats();
    }

    @Override
    public Optional<Pilot> getWinner(Long id) throws NotStartedRaceException {
        Race race = findById(id);

        return race.getWinner();
    }
}
