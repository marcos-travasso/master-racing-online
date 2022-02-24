package online.masterracing.services;

import online.masterracing.exceptions.RaceNotFoundException;
import online.masterracing.model.Race;

public interface RaceService extends CrudService<Race, Long>{
    void startRace(Long id) throws RaceNotFoundException;
}
