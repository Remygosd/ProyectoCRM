package org.bedu.proyectoCRM.controller;


import org.springframework.http.HttpStatus;
import org.bedu.proyectoCRM.model.Cliente;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> getCliente(@PathVariable long clienteId){
          
        /*Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNombre("Cliente");
        
        return ResponseEntity.ok(cliente);*/
        return  ResponseEntity.ok(new Cliente());
    } 

    @GetMapping
    public ResponseEntity <List<Cliente>> getCliente(){
        return  ResponseEntity.ok(new ArrayList<>());
    }

    @PostMapping
    public ResponseEntity<Void> creaCliente(@Valid @RequestBody Cliente cliente){
      return ResponseEntity.created(URI.create("")).build();
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Void> actualizaCliente( @PathVariable Long ClienteId, @Valid @RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> eliminaCliente( @PathVariable Long clienteId){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}