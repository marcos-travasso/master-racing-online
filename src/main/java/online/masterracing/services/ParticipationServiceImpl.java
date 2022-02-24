package online.masterracing.services;

import online.masterracing.exceptions.NotFoundException;
import online.masterracing.model.Participation;
import online.masterracing.model.RaceStats;
import online.masterracing.repositories.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ParticipationServiceImpl implements ParticipationService{

    private final ParticipationRepository participationRepository;
    private final RaceService raceService;
    private final PilotService pilotService;

    public ParticipationServiceImpl(ParticipationRepository participationRepository, RaceService raceService, PilotService pilotService) {
        this.participationRepository = participationRepository;
        this.raceService = raceService;
        this.pilotService = pilotService;
    }

    @Override
    public Set<Participation> findAll() {
        return new HashSet<>(participationRepository.findAll());
    }

    @Override
    public Participation findById(Long aLong) {
        return participationRepository.findById(aLong).orElseThrow(NotFoundException::new);
    }

    @Override
    public Participation save(Participation object) {
        object.setRace(raceService.findById(object.getRace().getId()));
        object.setPilot(pilotService.findById(object.getPilot().getId()));
        return participationRepository.save(object);
    }

    @Override
    public void delete(Participation object) {
        participationRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        participationRepository.deleteById(aLong);
    }

    @Override
    public void addLap(Long id) {
        Participation participation = findById(id);
        participation.addLap();
        save(participation);
    }

    @Override
    public RaceStats getStats(Long id) {
        Participation participation = findById(id);

        return participation.getStats();
    }
}
