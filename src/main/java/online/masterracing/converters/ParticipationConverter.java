package online.masterracing.converters;

import online.masterracing.model.Participation;
import online.masterracing.model.ParticipationDTO;
import online.masterracing.model.Pilot;
import online.masterracing.model.Race;

public class ParticipationConverter {
    private ParticipationConverter(){}

    public static Participation convertToParticipation(ParticipationDTO participationDTO){
        Participation participation = new Participation();
        participation.setId(participationDTO.getId());

        participation.setPilot(new Pilot());
        participation.getPilot().setId(participationDTO.getPilotId());
        participation.setRace(new Race());
        participation.getRace().setId(participationDTO.getRaceId());

        return participation;
    }

    public static ParticipationDTO convertToDTO(Participation participation){
        ParticipationDTO participationDTO = new ParticipationDTO();
        participationDTO.setId(participation.getId());
        participationDTO.setPilotId(participation.getPilot().getId());
        participationDTO.setRaceId(participation.getRace().getId());

        return participationDTO;
    }
}
