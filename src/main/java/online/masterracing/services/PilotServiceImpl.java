package online.masterracing.services;

import online.masterracing.exceptions.NotFoundException;
import online.masterracing.model.Lap;
import online.masterracing.model.Pilot;
import online.masterracing.model.RaceStats;
import online.masterracing.repositories.PilotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PilotServiceImpl implements PilotService{

    private final PilotRepository pilotRepository;

    public PilotServiceImpl(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    @Override
    public Set<Pilot> findAll() {
        return new HashSet<>(pilotRepository.findAll());
    }

    @Override
    public Pilot findById(Long aLong) {
        return pilotRepository.findById(aLong).orElseThrow(NotFoundException::new);
    }

    @Override
    public Pilot save(Pilot object) {
        return pilotRepository.save(object);
    }

    @Override
    public void delete(Pilot object) {
        pilotRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        pilotRepository.deleteById(aLong);
    }

    @Override
    public RaceStats getStats(Long id) {
        Pilot pilot = findById(id);

        List<Lap> allLaps = new ArrayList<>();
        pilot.getParticipation().forEach(participation -> allLaps.addAll(participation.getLaps()));

        return new RaceStats(allLaps);
    }
}
