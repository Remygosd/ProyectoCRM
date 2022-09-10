package org.bedu.proyectoCRM.services;

import org.bedu.proyectoCRM.controller.mappers.ClienteMapper;
import org.bedu.proyectoCRM.model.Cliente;
import org.bedu.proyectoCRM.persistence.ClienteRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public Cliente guardaCliente(Cliente cliente) {
        return mapper.clienteEntityToCliente(
                repository.save(mapper.clienteToClienteEntity(cliente)));
    }
    
    public Optional<Cliente> obtenCliente(long clienteId) {
        return repository.findById(clienteId)
                .map(cliente -> Optional.of(mapper.clienteEntityToCliente(cliente)))
                .orElse(Optional.empty());
    }

    public List<Cliente> obtenClientes(){
        return repository.findAll()
            .stream()
            .map(cliente -> mapper
                .clienteEntityToCliente(cliente))
                .collect(Collectors.toList());
    }


    public void eliminaCliente(long clienteId){
        repository.deleteById(clienteId);
    }

    public Cliente actualizaCliente(Cliente cliente){
        return mapper.clienteEntityToCliente(
                repository.save(mapper.clienteToClienteEntity(cliente))
        );
    }

    public long cuenteClientes(){
        return repository.count();
    }

}
