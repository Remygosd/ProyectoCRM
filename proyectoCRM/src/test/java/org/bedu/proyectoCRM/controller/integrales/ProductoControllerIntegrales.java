package org.bedu.proyectoCRM.controller.integrales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import org.bedu.proyectoCRM.model.Producto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ProductoControllerIntegrales {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test //Obtener Producto "OPTIONAL"
    public void obtenProductoTest() throws Exception {
        ResponseEntity<Producto> response = restTemplate.getForEntity("/producto/1", Producto.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getId(), equalTo(1L));
    }
 
    @Test
    public void getProductos() throws Exception {

        ResponseEntity<Producto[]> response = restTemplate.getForEntity("/producto", Producto[].class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody()[0].getId(), equalTo(1L));
        assertThat(response.getBody()[1].getId(), equalTo(2L));
    }

    
    @Test
    public void deleteProductoTest() throws Exception {
         restTemplate.delete("/producto/1" );
         ResponseEntity<Producto> response = restTemplate.getForEntity("/producto/1", Producto.class);
         assertThat(response.getStatusCode(), equalTo(HttpStatus.INTERNAL_SERVER_ERROR));
        
        
    }
    
}
