package org.bedu.proyectoCRM.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bedu.proyectoCRM.controller.mappers.VentaMapper;
import org.bedu.proyectoCRM.model.Venta;
import org.bedu.proyectoCRM.persistence.VentaRepository;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository repository;
    private final VentaMapper mapper;

    public Venta guardaVenta(Venta Venta) {
        return mapper.ventaEntityToVentaModel(
                repository.save(mapper.ventaModelToVentaEntity(Venta))
        );
    }

    public List<Venta> obtenVentas(){
        return repository.findAll().stream().map(venta -> mapper.ventaEntityToVentaModel(venta)).collect(Collectors.toList());
    }

    public Optional<Venta> obtenVenta(Long idVenta) {
        return repository.findById(idVenta)
                .map(venta -> Optional.of(mapper.ventaEntityToVentaModel(venta)))
                .orElse(Optional.empty());
    }

    public void eliminaVenta(Long idVenta){
        repository.deleteById(idVenta);
    }

    public Venta actualizaVenta(Venta venta){
        return mapper.ventaEntityToVentaModel(
                repository.save(mapper.ventaModelToVentaEntity(venta))
        );
    }

    public long cuenteVentas(){
        return repository.count();
    }
    
}
