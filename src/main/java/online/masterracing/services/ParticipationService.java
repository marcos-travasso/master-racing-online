package online.masterracing.services;

import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.exceptions.ParticipationNotFoundException;
import online.masterracing.exceptions.PilotFinishedRaceException;
import online.masterracing.model.Participation;

public interface ParticipationService extends CrudService<Participation, Long>{
    @Override
    Participation findById(Long aLong) throws ParticipationNotFoundException;

    void addLap(Long id) throws NotStartedRaceException, PilotFinishedRaceException;
}
