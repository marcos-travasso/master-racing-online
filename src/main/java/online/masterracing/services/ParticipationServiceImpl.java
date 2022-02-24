package online.masterracing.services;

import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.exceptions.ParticipationNotFoundException;
import online.masterracing.model.Participation;
import online.masterracing.model.RaceStats;
import online.masterracing.repositories.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ParticipationServiceImpl implements ParticipationService{

    private final ParticipationRepository participationRepository;

    public ParticipationServiceImpl(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    @Override
    public Set<Participation> findAll() {
        Set<Participation> participation = new HashSet<>();
        participationRepository.findAll().forEach(participation::add);
        return participation;
    }

    @Override
    public Participation findById(Long aLong) {
        return participationRepository.findById(aLong).orElseThrow(ParticipationNotFoundException::new);
    }

    @Override
    public Participation save(Participation object) {
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
    public void addLap(Long id) throws NotStartedRaceException {
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
