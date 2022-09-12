package org.bedu.proyectoCRM.services;


import org.bedu.proyectoCRM.controller.mappers.ProductoMapper;
import org.bedu.proyectoCRM.model.Producto;
import org.bedu.proyectoCRM.persistence.ProductoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductoService {
    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    public Producto guardaProducto(Producto producto) {
        return mapper.productoEntityToProducto(
                repository.save(mapper.productoToProductoEntity(producto))
        );
    }

    public List<Producto> obtenProductos(){
        return repository.findAll().stream().map(Producto -> mapper.productoEntityToProducto(Producto)).collect(Collectors.toList());
    }

    public Optional<Producto> obtenProducto(long idProducto) {
        return repository.findById(idProducto)
                .map(producto -> Optional.of(mapper.productoEntityToProducto(producto)))
                .orElse(Optional.empty());
    }

    public void eliminaProducto(Long idProducto){
        repository.deleteById(idProducto);
    }

    public Producto actualizaProducto(Producto producto){
        return mapper.productoEntityToProducto(
                repository.save(mapper.productoToProductoEntity(producto))
        );
    }

    public Long cuenteProductos(){
        return repository.count();
    }
}
