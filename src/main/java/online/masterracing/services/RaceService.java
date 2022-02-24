package online.masterracing.services;

import online.masterracing.exceptions.RaceNotFoundException;
import online.masterracing.model.Race;
import online.masterracing.model.RaceStats;

public interface RaceService extends CrudService<Race, Long>{
    @Override
    Race findById(Long aLong) throws RaceNotFoundException;

    void startRace(Long id);

    RaceStats getStats(Long id) throws RaceNotFoundException;
}
