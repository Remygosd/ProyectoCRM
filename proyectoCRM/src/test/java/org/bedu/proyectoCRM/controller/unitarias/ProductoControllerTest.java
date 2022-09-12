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

import org.bedu.proyectoCRM.controller.ProductoController;
import org.bedu.proyectoCRM.model.Producto;
import org.bedu.proyectoCRM.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@AutoConfigureRestDocs(uriScheme = "https", uriHost = "bedu.org/rest", uriPort = 80)
@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    void obtenProducto() throws Exception {
        given(productoService.obtenProducto(anyLong())).willReturn(Optional.of(Producto.builder()
            .id(1L)
            .nombre("La cubana")
            .categoria("novela")
            .precio(900)
            .numeroRegistro("123-234-4567")
            .fechaCreacion(LocalDateTime.now())
            .build()));
            
        ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(get("/producto/{productoId}", 1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.precio", is(900.0)))
                .andExpect(jsonPath("$.categoria", is("novela")))

                .andDo(document("producto/get-producto",
                        pathParameters(
                                parameterWithName("productoId").description("Identificador del producto")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identificador del producto"),
                                fieldWithPath("nombre").description("nombre del producto"),
                                fieldWithPath("categoria").description("categoria del producto"),
                                fieldWithPath("precio").description("precio del producto"),
                                fieldWithPath("numeroRegistro").description("registro del producto"),
                                fieldWithPath("fechaCreacion").description("registro del producto")
                        )));
    }

    @Test
    void getProductos() throws Exception {

        List<Producto> productos = Arrays.asList(
            Producto.builder().id(1L).nombre("La cubana").categoria("novela").precio(900).numeroRegistro("123-234-4567").fechaCreacion(LocalDateTime.now()).build(),
            Producto.builder().id(2L).nombre("La cubana").categoria("novela").precio(900).numeroRegistro("123-234-4567").fechaCreacion(LocalDateTime.now()).build(),
            Producto.builder().id(3L).nombre("La cubana").categoria("novela").precio(900).numeroRegistro("123-234-4567").fechaCreacion(LocalDateTime.now()).build()
            );
                

        given(productoService.obtenProductos()).willReturn(productos);

        ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(get("/producto")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andDo(document("producto/get-productos",
                        responseFields(
                            fieldWithPath("[].id").description("identificador del producto"),
                            fieldWithPath("[].nombre").description("nombre del producto"),
                            fieldWithPath("[].categoria").description("categoria del producto"),
                            fieldWithPath("[].precio").description("precio del producto"),
                            fieldWithPath("[].numeroRegistro").description("registro del producto"),
                            fieldWithPath("[].fechaCreacion").description("registro del producto")
                        )));
    }

    @Test
    void eliminaProducto() throws Exception {
        mockMvc.perform(delete("/producto/{productoId}", 1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())

                .andDo(document("producto/delete-producto",
                        pathParameters(
                                parameterWithName("productoId").description("Identificador del producto")
                        )));
    }

    @Test
    void creaProducto() throws Exception {
        Producto productoParametro = Producto.builder().id(1L).nombre("La cubana").categoria("novela").precio(900).numeroRegistro("232-243-1233").fechaCreacion(LocalDateTime.now()).build();
        Producto productoRespuesta = Producto.builder().nombre("La cubana").categoria("novela").precio(900).numeroRegistro("232-243-1233").fechaCreacion(LocalDateTime.now()).build();
        

        given(productoService.guardaProducto(productoParametro)).willReturn(productoRespuesta);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(post("/producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoParametro)))
                .andExpect(status().isCreated())

                .andDo(document("producto/post-producto",
                        requestFields(
                                fieldWithPath("id").description("identificador del producto"),
                                fieldWithPath("nombre").description("nombre del producto"),
                                fieldWithPath("categoria").description("categoria del producto"),
                                fieldWithPath("precio").description("precio del producto"),
                                fieldWithPath("numeroRegistro").description("registro del producto"),
                                fieldWithPath("fechaCreacion").description("registro del producto")
                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso su identificador generado")
                        ))
                );
    }
   
    @Test
    void actualizaProducto() throws Exception {

        Producto productoParametro = Producto.builder()
            .id(1L)
            .nombre("La cubana")
            .categoria("novela")
            .precio(900f)
            .numeroRegistro("333-333-2222")
            .fechaCreacion(LocalDateTime.now())
            .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(put("/producto/{productoId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("producto/put-producto",
                        pathParameters(
                                parameterWithName("productoId").description("Identificador del producto")
                        ),
                        requestFields(
                            fieldWithPath("id").description("identificador del producto"),
                            fieldWithPath("nombre").description("nombre del producto"),
                            fieldWithPath("categoria").description("categoria del producto"),
                            fieldWithPath("precio").description("precio del producto"),
                            fieldWithPath("numeroRegistro").description("registro del producto"),
                            fieldWithPath("fechaCreacion").description("fecha del producto")
                        )
                ));
    }

   
    
 
}
