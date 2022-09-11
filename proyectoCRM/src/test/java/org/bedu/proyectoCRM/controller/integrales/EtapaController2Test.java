package org.bedu.proyectoCRM.controller.integrales;

import org.bedu.proyectoCRM.model.Etapa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.hamcrest.Matchers.*;


import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EtapaController2Test {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void obtenEtapaTest() throws Exception {

        ResponseEntity<Etapa> response = restTemplate.getForEntity("/etapa/7", Etapa.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getEtapaId(), equalTo(7L));
        
    }
    @Test
    public void obtenEtapasTest() throws Exception {

        ResponseEntity<Etapa[]> response = restTemplate.getForEntity("/etapa", Etapa[].class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody()[0].getEtapaId(), equalTo(1L));
        assertThat(response.getBody()[1].getEtapaId(), equalTo(2L));
        assertThat(response.getBody()[2].getEtapaId(), equalTo(3L));
        assertThat(response.getBody()[3].getEtapaId(), equalTo(4L));
        assertThat(response.getBody()[4].getEtapaId(), equalTo(5L));
        assertThat(response.getBody()[5].getEtapaId(), equalTo(6L));
        assertThat(response.getBody()[6].getEtapaId(), equalTo(7L));
        
    }
    @Test
    public void createEtapaTest() throws Exception {
   Etapa etapa = Etapa.builder().nombre("Etapa Test").orden(7).build();
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity("/etapa", etapa, HttpStatus.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        
        
    }
    @Test
    public void updateEtapaTest() throws Exception {
   Etapa etapa = Etapa.builder().nombre("Etapa Test modificados").orden(10).build();
         restTemplate.put("/etapa/8", etapa);
         ResponseEntity<Etapa> response = restTemplate.getForEntity("/etapa/8", Etapa.class);
         assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getNombre(), equalTo("Etapa Test modificados"));
        assertThat(response.getBody().getOrden(), equalTo(10));
        
        
    }
    @Test
    public void deleteEtapaTest() throws Exception {
         restTemplate.delete("/etapa/8" );

         ResponseEntity<Etapa> response = restTemplate.getForEntity("/etapa/8", Etapa.class);
         assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
        
        
    }
    
}
