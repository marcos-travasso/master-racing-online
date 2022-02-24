package online.masterracing.converters;

import online.masterracing.model.Participation;
import online.masterracing.model.ParticipationDTO;

public class ParticipationConverter {
    private ParticipationConverter(){}

    public static Participation convertToParticipation(ParticipationDTO participationDTO){
        Participation participation = new Participation();
        participation.setId(participationDTO.getId());
        participation.setPilot(PilotConverter.convertToPilot(participationDTO.getPilot()));
        participation.setRace(RaceConverter.convertToRace(participationDTO.getRace()));

        return participation;
    }

    public static ParticipationDTO convertToDTO(Participation participation){
        ParticipationDTO participationDTO = new ParticipationDTO();
        participationDTO.setId(participationDTO.getId());
        participationDTO.setPilot(PilotConverter.convertToDTO(participation.getPilot()));
        participationDTO.setRace(RaceConverter.convertToDTO(participation.getRace()));

        return participationDTO;
    }
}
