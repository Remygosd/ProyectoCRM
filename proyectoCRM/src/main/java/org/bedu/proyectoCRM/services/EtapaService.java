package org.bedu.proyectoCRM.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bedu.proyectoCRM.controller.mappers.EtapaMapper;
import org.bedu.proyectoCRM.model.Etapa;
import org.bedu.proyectoCRM.persistence.EtapaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtapaService {
    private final EtapaRepository repository;
    private final EtapaMapper mapper;

    public Etapa guardaEtapa(Etapa etapa) {
        return mapper.etapaEntityToEtapaModel(
                repository.save(mapper.etapaModelToEtapaEntity(etapa))
        );
    }

    public List<Etapa> obtenEtapas(){
       
        return repository.findAll().stream().map(etapa -> mapper.etapaEntityToEtapaModel(etapa)).collect(Collectors.toList());
    }

    public Optional<Etapa> obtenEtapa(Long idEtapa) {
        return repository.findById(idEtapa)
                .map(Etapa -> Optional.of(mapper.etapaEntityToEtapaModel(Etapa)))
                .orElse(Optional.empty());
    }

    public void eliminaEtapa(Long idEtapa){
        repository.deleteById(idEtapa);
    }

    public Etapa actualizaEtapa(Etapa etapa){
        return mapper.etapaEntityToEtapaModel(
                repository.save(mapper.etapaModelToEtapaEntity(etapa))
        );
    }

    public Long cuenteEtapas(){
        return repository.count();
    }

}
