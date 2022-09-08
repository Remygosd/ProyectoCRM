package org.bedu.proyectoCRM.controller;

import org.bedu.proyectoCRM.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @GetMapping("/{productoId}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long productoId){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos(){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @PostMapping
    public ResponseEntity<Void> crearProducto(@RequestBody Producto producto){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable Long productoId){
        return ResponseEntity.created(URI.create("1")).build();
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long productoId){
        return ResponseEntity.created(URI.create("1")).build();
    }


}
