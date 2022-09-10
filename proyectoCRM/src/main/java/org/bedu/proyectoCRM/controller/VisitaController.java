package org.bedu.proyectoCRM.controller;

import org.bedu.proyectoCRM.model.Visita;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/visita")
public class VisitaController {

    @GetMapping("/{visitaId}")
    public ResponseEntity<Visita> getVisita(@PathVariable Long visitaId){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @GetMapping
    public ResponseEntity<List<Visita>> getVisitas(){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @PostMapping
    public ResponseEntity<Void> crearVisita(@RequestBody Visita visita){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @PutMapping("/{visitaId}")
    public ResponseEntity<Void> actualizarVisita(@PathVariable Long visitaId){
        return ResponseEntity.created(URI.create("1")).build();
    }

    @DeleteMapping("/{visitaId}")
    public ResponseEntity<Void> eliminarVisita(@PathVariable Long visitaId){
        return ResponseEntity.created(URI.create("1")).build();
    }
}
