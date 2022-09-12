package org.bedu.proyectoCRM.controller.unitarias;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bedu.proyectoCRM.controller.VisitaController;
import org.bedu.proyectoCRM.controller.mappers.ClienteMapper;

import org.bedu.proyectoCRM.model.Cliente;
import org.bedu.proyectoCRM.model.Visita;

import org.bedu.proyectoCRM.persistence.entities.ClienteEntity;
import org.bedu.proyectoCRM.services.VisitaService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@AutoConfigureRestDocs
@WebMvcTest(VisitaController.class)
public class VisitaControllerTest {

    private ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitaService visitaService;

    
    @Test
    void getVisita() throws Exception {

        ClienteEntity cliente1 = clienteMapper.clienteToClienteEntity(Cliente.builder().nombre("Fanny").correoContacto("fanny.f@gmail.com").numeroEmpleados("200").direccion("Anahuac211").build());
        

        given(visitaService.obtenVisita(anyLong())).willReturn(Optional.of(Visita.builder().id(1L).cliente(cliente1).fechaProgramada(LocalDateTime.now()).direccion("Esmeralda 1024").proposito("Realizar deportes extremos").vendedor("Athos").build()));

        


        mockMvc.perform(get("/visita/{id}",1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.direccion", is("Esmeralda 1024")))
                .andExpect(jsonPath("$.proposito", is("Realizar deportes extremos")))
                .andExpect(jsonPath("$.vendedor", is("Athos")))

                .andDo(document("visita/get-visita",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la visita")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador de la visita"),
                                fieldWithPath("cliente.id").ignored(),
                                fieldWithPath("cliente.nombre").ignored(),
                                fieldWithPath("cliente.correoContacto").ignored(),
                                fieldWithPath("cliente.numeroEmpleados").ignored(),
                                fieldWithPath("cliente.direccion").ignored(),
                                
                                fieldWithPath("fechaProgramada").description("numero de fechaProgramada de la visita"),
                                fieldWithPath("direccion").description("identificador de la visita"),
                                fieldWithPath("proposito").description("cliente de la visita"),
                                fieldWithPath("vendedor").description("numero de fechaProgramada de la visita")
                             
                        )));
    }

    @Test
    void getVisitas() throws Exception {

        ClienteEntity cliente1 = clienteMapper.clienteToClienteEntity(Cliente.builder().id(1L).nombre("Fanny").correoContacto("fanny.f@gmail.com").numeroEmpleados("200").direccion("Anahuac211").build());


        List<Visita> visitas = Arrays.asList(
                Visita.builder().id(1L).cliente(cliente1).fechaProgramada(LocalDateTime.now()).direccion("Esmeralda 1024").proposito("Realizar deportes extremos").vendedor("Athos").build(),
                Visita.builder().id(2L).cliente(cliente1).fechaProgramada(LocalDateTime.now()).direccion("Esmeralda 1024").proposito("Realizar deportes extremos").vendedor("Athos").build(),
                Visita.builder().id(3L).cliente(cliente1).fechaProgramada(LocalDateTime.now()).direccion("Esmeralda 1024").proposito("Realizar deportes extremos").vendedor("Athos").build()
        );

        given(visitaService.obtenVisitas()).willReturn(visitas);

        mockMvc.perform(get("/visita")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[0].direccion", is("Esmeralda 1024")))
                .andExpect(jsonPath("$[1].direccion", is("Esmeralda 1024")))
                .andExpect(jsonPath("$[2].direccion", is("Esmeralda 1024")))
                .andExpect(jsonPath("$[0].proposito", is("Realizar deportes extremos")))
                .andExpect(jsonPath("$[1].proposito", is("Realizar deportes extremos")))
                .andExpect(jsonPath("$[2].proposito", is("Realizar deportes extremos")))
                .andExpect(jsonPath("$[0].vendedor", is("Athos")))
                .andExpect(jsonPath("$[1].vendedor", is("Athos")))
                .andExpect(jsonPath("$[2].vendedor", is("Athos")))
                
                .andDo(document("visita/get-visitas",
               
                responseFields(
                        fieldWithPath("[].id").description("identificador de la visita"),
                        fieldWithPath("[].cliente").description("Cliente"),
                        fieldWithPath("[].cliente.id").ignored(),
                        fieldWithPath("[].cliente.nombre").ignored(),
                        fieldWithPath("[].cliente.correoContacto").ignored(),
                        fieldWithPath("[].cliente.numeroEmpleados").ignored(),
                        fieldWithPath("[].cliente.direccion").ignored(),
                        fieldWithPath("[].fechaProgramada").description("numero de fechaProgramada de la visita"),
                        fieldWithPath("[].direccion").description("identificador de la visita"),
                        fieldWithPath("[].proposito").description("cliente de la visita"),
                        fieldWithPath("[].vendedor").description("numero de fechaProgramada de la visita")

                        
                        
                        
                )));
    }
 
    @Test
    void creaVisita() throws Exception {

        ClienteEntity cliente1 = clienteMapper.clienteToClienteEntity(Cliente.builder().nombre("Fanny").correoContacto("fanny.f@gmail.com").numeroEmpleados("200").direccion("Anahuac211").build());

        Visita visitaParametro = Visita.builder().cliente(cliente1).fechaProgramada(LocalDateTime.now()).direccion("Esmeralda 1024").proposito("Realizar deportes extremos").vendedor("Athos").build();
        Visita visitaRespuesta = Visita.builder().id(1L).cliente(cliente1).fechaProgramada(LocalDateTime.now()).direccion("Esmeralda 1024").proposito("Realizar deportes extremos").vendedor("Athos").build();

        given(visitaService.guardaVisita(visitaParametro)).willReturn(visitaRespuesta);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(post("/visita")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(visitaParametro)))
                .andExpect(status().isCreated())

                .andDo(document("visita/post-visita",
                        requestFields(
                            fieldWithPath("id").description("identificador de la visita"),
                            fieldWithPath("cliente.id").ignored(),
                            fieldWithPath("cliente.nombre").ignored(),
                            fieldWithPath("cliente.correoContacto").ignored(),
                            fieldWithPath("cliente.numeroEmpleados").ignored(),
                            fieldWithPath("cliente.direccion").ignored(),
                            
                            fieldWithPath("fechaProgramada").description("numero de fechaProgramada de la visita"),
                            fieldWithPath("direccion").description("identificador de la visita"),
                            fieldWithPath("proposito").description("cliente de la visita"),
                            fieldWithPath("vendedor").description("numero de fechaProgramada de la visita")
                              
                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }

    @Test
    void actualizaVisita() throws Exception {

        ClienteEntity cliente1 = clienteMapper.clienteToClienteEntity(Cliente.builder().nombre("Fanny").correoContacto("fanny.f@gmail.com").numeroEmpleados("200").direccion("Anahuac211").build());

        Visita visitaParametro = Visita.builder().id(1L).cliente(cliente1).fechaProgramada(LocalDateTime.now()).direccion("Esmeralda 1024").proposito("Realizar deportes extremos").vendedor("Athos").build();
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(put("/visita/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(visitaParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("visita/put-visita",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la visita")
                        ),
                        requestFields(
                        fieldWithPath("id").description("identificador de la visita"),
                        fieldWithPath("id").description("identificador de la visita"),
                        fieldWithPath("cliente.id").ignored(),
                        fieldWithPath("cliente.nombre").ignored(),
                        fieldWithPath("cliente.correoContacto").ignored(),
                        fieldWithPath("cliente.numeroEmpleados").ignored(),
                        fieldWithPath("cliente.direccion").ignored(),
                        
                        fieldWithPath("fechaProgramada").description("numero de fechaProgramada de la visita"),
                        fieldWithPath("direccion").description("identificador de la visita"),
                        fieldWithPath("proposito").description("cliente de la visita"),
                        fieldWithPath("vendedor").description("numero de fechaProgramada de la visita")
                          
                          )
                ));
    }
 
    @Test
    void eliminaVisita() throws Exception {
        mockMvc.perform(delete("/visita/{id}",1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andDo(document("visita/delete-visita",
                        pathParameters(
                                parameterWithName("id").description("Identificador de la visita")
                        )));
    }

  
}