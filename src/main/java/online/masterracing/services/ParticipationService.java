package online.masterracing.services;

import online.masterracing.exceptions.NotFoundException;
import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.exceptions.PilotFinishedRaceException;
import online.masterracing.model.Participation;
import online.masterracing.model.RaceStats;

public interface ParticipationService extends CrudService<Participation, Long>{
    @Override
    Participation findById(Long aLong) throws NotFoundException;

    void addLap(Long id) throws NotStartedRaceException, PilotFinishedRaceException;

    RaceStats getStats(Long id);
}
