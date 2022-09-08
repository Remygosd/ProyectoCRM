package org.bedu.proyectoCRM.controller;

import org.bedu.proyectoCRM.model.Etapa;
import org.bedu.proyectoCRM.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/etapa")
public class EtapaController {

    @GetMapping("/{etapaId}")
    public ResponseEntity<Etapa> getEtapa(@PathVariable Long etapaId){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @GetMapping
    public ResponseEntity<List<Etapa>> getEtapas(){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @PostMapping
    public ResponseEntity<Void> crearEtapa(@RequestBody Etapa etapa){

        return ResponseEntity.created(URI.create("1")).build();
    }

    @PutMapping("/{etapaId}")
    public ResponseEntity<Void> actualizarEtapa(@PathVariable Long etapaId){
        return ResponseEntity.created(URI.create("1")).build();
    }

    @DeleteMapping("/{etapaId}")
    public ResponseEntity<Void> eliminarEtapa(@PathVariable Long etapaId){
        return ResponseEntity.created(URI.create("1")).build();
    }
}
