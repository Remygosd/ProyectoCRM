package org.bedu.proyectoCRM.runners;

import java.util.Arrays;
import java.util.List;

import org.bedu.proyectoCRM.persistence.ClienteRepository;
import org.bedu.proyectoCRM.persistence.entity.ClienteEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Component
public class ClienteRunner implements CommandLineRunner{

    private final ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        ClienteEntity cliente1 = creaCliente("Fanny","fanny.f@gmail.com","200", "Anahuac211");
        ClienteEntity cliente2 = creaCliente("Lola","pds.f@gmail.com","30", "Leon 12");
        ClienteEntity cliente3 = creaCliente("Nestor","nestor.f@gmail.com","100", " Madrid 91");

        List<ClienteEntity> cliente = Arrays.asList(cliente1, cliente2, cliente3);

        clienteRepository.saveAll(cliente);   
    }
    
    private ClienteEntity creaCliente(String nombre, String correoContacto,String numeroEmpleados, String direccion) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNombre(nombre);
        clienteEntity.setCorreoContacto(correoContacto);
        clienteEntity.setNumeroEmpleados(numeroEmpleados);
        clienteEntity.setDireccion(direccion);
        return clienteEntity;
    }
}
