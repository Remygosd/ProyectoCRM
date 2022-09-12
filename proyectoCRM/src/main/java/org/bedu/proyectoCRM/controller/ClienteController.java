package org.bedu.proyectoCRM.controller;


import org.springframework.http.HttpStatus;
import org.bedu.proyectoCRM.model.Cliente;
import org.bedu.proyectoCRM.services.ClienteService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor

public class ClienteController {
    
    private final ClienteService clienteService;
    
    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long clienteId){
          
        Optional<Cliente> cliente = clienteService.obtenCliente(clienteId);

        return  ResponseEntity.ok(cliente.get());
    } 

    @GetMapping
    public ResponseEntity <List<Cliente>> getClientes(){

        return  ResponseEntity.ok(clienteService.obtenClientes());
    }

    @PostMapping
    public ResponseEntity<Void> creaCliente(@Valid @RequestBody Cliente cliente) {
        Cliente clienteNuevo = clienteService.guardaCliente(cliente);
        return ResponseEntity.created(URI.create(String.valueOf(clienteNuevo.getId()))).build();
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Void> actualizaCliente( @RequestBody @Valid Cliente cliente) {
        clienteService.actualizaCliente(cliente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminaCliente( @PathVariable Long clienteId){
        clienteService.eliminaCliente(clienteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}