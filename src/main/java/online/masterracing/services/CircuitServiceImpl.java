package online.masterracing.services;

import online.masterracing.model.Circuit;
import online.masterracing.repositories.CircuitRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CircuitServiceImpl implements CircuitService {

    private final CircuitRepository circuitRepository;

    public CircuitServiceImpl(CircuitRepository circuitRepository) {
        this.circuitRepository = circuitRepository;
    }

    @Override
    public Set<Circuit> findAll() {
        Set<Circuit> circuits = new HashSet<>();
        circuitRepository.findAll().forEach(circuits::add);
        return circuits;
    }

    @Override
    public Circuit findById(Long aLong) {
        return circuitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Circuit save(Circuit object) {
        return circuitRepository.save(object);
    }

    @Override
    public void delete(Circuit object) {
        circuitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        circuitRepository.deleteById(aLong);
    }
}
