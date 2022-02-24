package online.masterracing.services;

import online.masterracing.exceptions.RaceNotFoundException;
import online.masterracing.model.Race;

public interface RaceService extends CrudService<Race, Long>{
    @Override
    Race findById(Long aLong) throws RaceNotFoundException;

    void startRace(Long id);
}
