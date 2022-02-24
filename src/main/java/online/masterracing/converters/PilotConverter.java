package online.masterracing.converters;

import online.masterracing.model.Pilot;
import online.masterracing.model.PilotDTO;

public class PilotConverter {
    private PilotConverter(){

    }

    public static Pilot convertToPilot(PilotDTO pilotDTO){
        Pilot pilot = new Pilot();
        pilot.setId(pilotDTO.getId());
        pilot.setName(pilotDTO.getName());
        return pilot;
    }

    public static PilotDTO convertToDTO(Pilot pilot){
        PilotDTO pilotDTO = new PilotDTO();
        pilotDTO.setName(pilot.getName());
        pilotDTO.setId(pilot.getId());
        return pilotDTO;
    }
}
