package org.bedu.proyectoCRM.services;

import lombok.RequiredArgsConstructor;

import org.bedu.proyectoCRM.controller.mappers.VisitaMapper;
import org.bedu.proyectoCRM.model.Visita;
import org.bedu.proyectoCRM.persistence.VisitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitaService {
    private final VisitaRepository repository;
    private final VisitaMapper mapper;

    public Visita guardaVisita(Visita visita) {
        return mapper.visitaEntityToVisita(
                repository.save(mapper.visitaToVisitaEntity(visita))
        );
    }

    public List<Visita> obtenVisitas(){
        return repository.findAll().stream().map(visita -> mapper.visitaEntityToVisita(visita)).collect(Collectors.toList());
    }

    public Optional<Visita> obtenVisita(long idVisita) {
        return repository.findById(idVisita)
                .map(Visita -> Optional.of(mapper.visitaEntityToVisita(Visita)))
                .orElse(Optional.empty());
    }

    public void eliminaVisita(long idVisita){
        repository.deleteById(idVisita);
    }

    public Visita actualizaVisita(Visita visita){
        return mapper.visitaEntityToVisita(
                repository.save(mapper.visitaToVisitaEntity(visita))
        );
    }

    public long cuenteVisitas(){
        return repository.count();
    }
}