package org.bedu.proyectoCRM.controller.integrales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.bedu.proyectoCRM.model.Cliente;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ClienteControllerIntegrales {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test //Obtener cliente "OPTIONAL"
    public void obtenClienteTest() throws Exception {
        ResponseEntity<Cliente> response = restTemplate.getForEntity("/cliente/1", Cliente.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getId(), equalTo(1L));
    }

    @Test
    public void getClientes() throws Exception {

        ResponseEntity<Cliente[]> response = restTemplate.getForEntity("/cliente", Cliente[].class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody()[0].getId(), equalTo(1L));
        assertThat(response.getBody()[1].getId(), equalTo(2L));
        assertThat(response.getBody()[2].getId(), equalTo(3L));
        assertThat(response.getBody()[3].getId(), equalTo(4L));

    }
    /*  
    @Test
    public void createCliente() throws Exception {
        Cliente cliente = Cliente.builder()
            .nombre("Nombre 1")
            .correoContacto("contacto@cliente1.com")
            .numeroEmpleados("30")
            .direccion("Direccion 1")
            .build();
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity("/cliente", cliente, HttpStatus.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        
    }*/

    @Test 
    public void actualizaClienteTest() throws Exception {
        Cliente cliente = Cliente.builder().id(1L).nombre("Nombre 1").direccion("Direccion 1").numeroEmpleados("10").correoContacto("contacto@cliente1.com").build();
        restTemplate.put("/cliente/1", cliente);
         ResponseEntity<Cliente> response = restTemplate.getForEntity("/cliente/1", Cliente.class);
         assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
    @Test
    public void deleteClienteTest() throws Exception {
         restTemplate.delete("/cliente/1" );
         ResponseEntity<Cliente> response = restTemplate.getForEntity("/cliente/1", Cliente.class);
         assertThat(response.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
        
        
    }

}
