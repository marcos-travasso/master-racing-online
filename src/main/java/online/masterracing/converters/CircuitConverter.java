package online.masterracing.converters;

import online.masterracing.model.Circuit;
import online.masterracing.model.CircuitDTO;

public class CircuitConverter {
    private CircuitConverter(){

    }

    public static Circuit convertToCircuit(CircuitDTO circuitDTO){
        Circuit circuit = new Circuit();
        circuit.setName(circuitDTO.getName());
        circuit.setId(circuitDTO.getId());
        return circuit;
    }

    public static CircuitDTO convertToDTO(Circuit circuit){
        CircuitDTO circuitDTO = new CircuitDTO();
        circuitDTO.setName(circuit.getName());
        circuitDTO.setId(circuit.getId());
        return circuitDTO;
    }
}
