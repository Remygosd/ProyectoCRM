package org.bedu.proyectoCRM.controller.mappers;

import org.bedu.proyectoCRM.model.Cliente;
import org.bedu.proyectoCRM.persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente clienteEntityToCliente(ClienteEntity clienteEntity);

    ClienteEntity clienteToClienteEntity(Cliente cliente);
}
