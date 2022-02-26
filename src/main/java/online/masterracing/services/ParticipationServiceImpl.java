package online.masterracing.services;

import online.masterracing.exceptions.NotFoundException;
import online.masterracing.exceptions.PilotAlreadyInRaceException;
import online.masterracing.model.Participation;
import online.masterracing.model.Pilot;
import online.masterracing.model.Race;
import online.masterracing.model.RaceStats;
import online.masterracing.repositories.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ParticipationServiceImpl implements ParticipationService{

    private ParticipationRepository participationRepository;
    private RaceService raceService;
    private PilotService pilotService;

    private ParticipationServiceImpl() {
    }

    @Autowired
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
        return participationRepository.save(object);
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

    @Override
    public Participation addParticipation(Participation object) {
        Pilot pilot = pilotService.findById(object.getPilot().getId());
        Race race = raceService.findById(object.getRace().getId());

        if(pilot.isInRace(race)){
            throw new PilotAlreadyInRaceException();
        }

        object.setPilot(pilot);
        object.setRace(race);

        return participationRepository.save(object);
    }
}
