package org.bedu.proyectoCRM.controller;

import org.bedu.proyectoCRM.model.Cliente;
import org.bedu.proyectoCRM.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void creaCliente() throws Exception {
        Cliente clienteParametro = Cliente.builder().nombre("Nombre").direccion("Direccion").numeroEmpleados("10").correoContacto("contacto@cliente.com").build();
        Cliente clienteRespuesta = Cliente.builder().id(1L).nombre("Nombre").direccion("Direccion").numeroEmpleados("10").correoContacto("contacto@cliente.com").build();

        BDDMockito.given(clienteService.guardaCliente(clienteParametro)).willReturn(clienteRespuesta);

        mockMvc.perform(post("/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getCliente() throws Exception {

        BDDMockito.given(clienteService.obtenCliente(anyLong()))
            .willReturn(Optional.of(Cliente.builder()
                .id(1L)
                .nombre("Fanny")
                .correoContacto("fanny@ust.com")
                .build()));

        mockMvc.perform(get("/cliente/1")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.correoContacto", is("fany@ust.com")))
                .andExpect(jsonPath("$.nombre", is("Fanny")));
    }
/*
    @Test
    public void actualizaCliente() throws Exception {

        Cliente clienteParametro = Cliente.builder()
                    .id(1L)
                    .nombre("Fanny")
                    .direccion("Direccion")
                    .correoContacto("contacto@cliente.com")
                    .numeroEmpleados("22")
                    .build();

        mockMvc.perform(put("/cliente/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(clienteParametro)))
                .andExpect(status().isNoContent());
    }*/

    @Test
    void eliminaCliente() throws Exception {
        mockMvc.perform(delete("/cliente/1")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }

    
   
}  

