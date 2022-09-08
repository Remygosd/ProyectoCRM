package org.bedu.proyectoCRM.controller;

import org.bedu.proyectoCRM.model.Producto;
import org.bedu.proyectoCRM.model.Venta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @GetMapping("/{ventaId}")
    public ResponseEntity<Venta> getVenta(@PathVariable Long ventaId){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @GetMapping
    public ResponseEntity<List<Venta>> getVentas(){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @PostMapping
    public ResponseEntity<Void> crearVenta(@RequestBody Venta venta){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @PutMapping("/{ventaId}")
    public ResponseEntity<Void> actualizarVenta(@PathVariable Long ventaId){
        return ResponseEntity.created(URI.create("1")).build();
    }

    @DeleteMapping("/{ventaId}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long ventaId){
        return ResponseEntity.created(URI.create("1")).build();
    }
}
