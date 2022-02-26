package online.masterracing.services;

import online.masterracing.exceptions.NotFoundException;
import online.masterracing.exceptions.NotStartedRaceException;
import online.masterracing.exceptions.RaceAlreadyStartedException;
import online.masterracing.model.Pilot;
import online.masterracing.model.Race;
import online.masterracing.model.RaceStats;

import java.util.Optional;

public interface RaceService extends CrudService<Race, Long>{
    void startRace(Long id) throws RaceAlreadyStartedException, NotFoundException;

    RaceStats getStats(Long id) throws NotFoundException;

    Optional<Pilot> getWinner(Long id) throws NotStartedRaceException;
}
