package online.masterracing.services;

import online.masterracing.exceptions.NotFoundException;
import online.masterracing.model.Pilot;
import online.masterracing.model.RaceStats;

public interface PilotService extends CrudService<Pilot, Long>{
    RaceStats getStats(Long id) throws NotFoundException;
}
