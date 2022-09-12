package org.bedu.proyectoCRM.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.bedu.proyectoCRM.model.Producto;
import org.bedu.proyectoCRM.services.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor

public class ProductoController {

    private final ProductoService productoService;
    
    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long productoId){
          
        Optional<Producto> producto = productoService.obtenProducto(productoId);

        return  ResponseEntity.ok(producto.get());
    } 

    @GetMapping
    public ResponseEntity <List<Producto>> getProductos(){

        return  ResponseEntity.ok(productoService.obtenProductos());
    }

    @PostMapping
    public ResponseEntity<Void> creaProducto(@Valid @RequestBody Producto producto) {
        Producto productoNuevo = productoService.guardaProducto(producto);
        return ResponseEntity.created(URI.create(String.valueOf(productoNuevo.getId()))).build();
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<Void> actualizaProducto( @RequestBody @Valid Producto producto) {
        productoService.actualizaProducto(producto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<Void> eliminaProducto( @PathVariable Long productoId){
        productoService.eliminaProducto(productoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
