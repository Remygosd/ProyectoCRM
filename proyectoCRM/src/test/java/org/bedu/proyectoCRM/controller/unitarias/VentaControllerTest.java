package org.bedu.proyectoCRM.controller.unitarias;


import org.bedu.proyectoCRM.controller.VentaController;
import org.bedu.proyectoCRM.controller.mappers.ClienteMapper;
import org.bedu.proyectoCRM.controller.mappers.ProductoMapper;
import org.bedu.proyectoCRM.model.Cliente;
import org.bedu.proyectoCRM.model.Producto;
import org.bedu.proyectoCRM.model.Venta;
import org.bedu.proyectoCRM.persistence.entities.ClienteEntity;
import org.bedu.proyectoCRM.persistence.entities.ProductoEntity;
import org.bedu.proyectoCRM.services.VentaService;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AutoConfigureRestDocs
@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaService ventaService;

    private ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);

    private ProductoMapper productoMapper = Mappers.getMapper(ProductoMapper.class);

    

    @Test
    void getVenta() throws Exception {
        ClienteEntity cliente1 = clienteMapper.clienteToClienteEntity(Cliente.builder().id(1L).nombre("Fanny").correoContacto("fanny.f@gmail.com").numeroEmpleados("200").direccion( "Anahuac211").build());
       
        ProductoEntity producto1 = productoMapper.productoToProductoEntity(Producto.builder().id(1L).nombre("Ciencia").categoria("Libro").precio(900).numeroRegistro("212-422-2229").fechaCreacion(LocalDateTime.now()).build());
        ProductoEntity producto2 = productoMapper.productoToProductoEntity(Producto.builder().id(2L).nombre("Animacion").categoria("Serie").precio(100).numeroRegistro("212-422-2222").fechaCreacion(LocalDateTime.now()).build());
        List<ProductoEntity> productos = Arrays.asList(producto1, producto2);
        given(ventaService.obtenVenta(anyLong())).willReturn(Optional.of(Venta.builder().ventaId(1L).monto(599f).cliente(cliente1).productos(productos).fechaCreacion(LocalDateTime.now()).build()));

        mockMvc.perform(get("/venta/{ventaId}",1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.ventaId", is(1)))
                .andExpect(jsonPath("$.monto", is(599.0)))
               

                .andDo(document("venta/get-venta",
                        pathParameters(
                                parameterWithName("ventaId").description("Identificador de la venta")
                        ),
                        responseFields(
                                fieldWithPath("ventaId").description("identificador de la venta"),
                                fieldWithPath("monto").description("monto total de la venta"),
                                fieldWithPath("fechaCreacion").description("Fecha en la que se realizó la venta"),
                                fieldWithPath("cliente").description("cliente que realizó la venta"),
                                fieldWithPath("cliente.id").ignored(),
                                fieldWithPath("cliente.nombre").ignored(),
                                fieldWithPath("cliente.correoContacto").ignored(),
                                fieldWithPath("cliente.numeroEmpleados").ignored(),
                                fieldWithPath("cliente.direccion").ignored(),
                                fieldWithPath("productos[]").description("Productos comprados"),
                                fieldWithPath("productos[].id").ignored(),
                                fieldWithPath("productos[].nombre").ignored(),
                                fieldWithPath("productos[].categoria").ignored(),
                                fieldWithPath("productos[].precio").ignored(),
                                fieldWithPath("productos[].numeroRegistro").ignored(),
                                fieldWithPath("productos[].fechaCreacion").ignored()
                           
                             
                        )));
    }

    @Test
    void getVentas() throws Exception {
        ClienteEntity cliente1 = clienteMapper.clienteToClienteEntity(Cliente.builder().id(1L).nombre("Fanny").correoContacto("fanny.f@gmail.com").numeroEmpleados("200").direccion( "Anahuac211").build());
        ClienteEntity cliente2 = clienteMapper.clienteToClienteEntity(Cliente.builder().id(2L).nombre("Lola").correoContacto("pds.f@gmail.com").numeroEmpleados("30").direccion( "Leon 12").build());
        ProductoEntity producto1 = productoMapper.productoToProductoEntity(Producto.builder().id(1L).nombre("Ciencia").categoria("Libro").precio(900).numeroRegistro("212-422-2229").fechaCreacion(LocalDateTime.now()).build());
        ProductoEntity producto2 = productoMapper.productoToProductoEntity(Producto.builder().id(2L).nombre("Animacion").categoria("Serie").precio(100).numeroRegistro("212-422-2222").fechaCreacion(LocalDateTime.now()).build());
        List<ProductoEntity> productos = Arrays.asList(producto1, producto2);
      
        List<Venta> ventas = Arrays.asList(
                Venta.builder().ventaId(1L).monto(599f).cliente(cliente1).productos(productos).fechaCreacion(LocalDateTime.now()).build(),
                Venta.builder().ventaId(2L).monto(600).cliente(cliente2).productos(productos).fechaCreacion(LocalDateTime.now()).build(),
                Venta.builder().ventaId(3L).monto(700).cliente(cliente1).productos(productos).fechaCreacion(LocalDateTime.now()).build()
        );

        given(ventaService.obtenVentas()).willReturn(ventas);

        mockMvc.perform(get("/venta")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].ventaId", is(1)))
                .andExpect(jsonPath("$[1].ventaId", is(2)))
                .andExpect(jsonPath("$[2].ventaId", is(3)))
                
                
                
                .andDo(document("venta/get-ventas",
               
                responseFields(
                        fieldWithPath("[].ventaId").description("identificador de la venta"),
                        fieldWithPath("[].monto").description("Monto total de la venta"),
                        fieldWithPath("[].fechaCreacion").description("Fecha de la venta"),
                        fieldWithPath("[].cliente").description("cliente que realizó la venta"),
                        fieldWithPath("[].cliente.id").ignored(),
                        fieldWithPath("[].cliente.nombre").ignored(),
                        fieldWithPath("[].cliente.correoContacto").ignored(),
                        fieldWithPath("[].cliente.numeroEmpleados").ignored(),
                        fieldWithPath("[].cliente.direccion").ignored(),
                        fieldWithPath("[].productos[]").description("Productos comprados"),
                        fieldWithPath("[].productos[].id").ignored(),
                        fieldWithPath("[].productos[].nombre").ignored(),
                        fieldWithPath("[].productos[].categoria").ignored(),
                        fieldWithPath("[].productos[].precio").ignored(),
                        fieldWithPath("[].productos[].numeroRegistro").ignored(),
                        fieldWithPath("[].productos[].fechaCreacion").ignored()
                     
                )));
    }

    @Test
    void creaVenta() throws Exception {

        ClienteEntity cliente1 = clienteMapper.clienteToClienteEntity(Cliente.builder().id(1L).nombre("Fanny").correoContacto("fanny.f@gmail.com").numeroEmpleados("200").direccion( "Anahuac211").build());
     
        ProductoEntity producto1 = productoMapper.productoToProductoEntity(Producto.builder().id(1L).nombre("Ciencia").categoria("Libro").precio(900).numeroRegistro("212-422-2229").fechaCreacion(LocalDateTime.now()).build());
        ProductoEntity producto2 = productoMapper.productoToProductoEntity(Producto.builder().id(2L).nombre("Animacion").categoria("Serie").precio(100).numeroRegistro("212-422-2222").fechaCreacion(LocalDateTime.now()).build());
        List<ProductoEntity> productos = Arrays.asList(producto1, producto2);
        Venta ventaParametro = Venta.builder().monto(599f).cliente(cliente1).productos(productos).fechaCreacion(LocalDateTime.now()).build();
        Venta ventaRespuesta = Venta.builder().ventaId(1L).monto(599f).cliente(cliente1).productos(productos).fechaCreacion(LocalDateTime.now()).build();

        given(ventaService.guardaVenta(ventaParametro)).willReturn(ventaRespuesta);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(post("/venta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ventaParametro)))
                .andExpect(status().isCreated())

                .andDo(document("venta/post-venta",
                        requestFields(
                                fieldWithPath("ventaId").description("identificador de la venta"),
                                fieldWithPath("monto").description("monto total de la venta"),
                                fieldWithPath("fechaCreacion").description("Fecha en la que se realizó la venta"),
                                fieldWithPath("cliente").description("cliente que realizó la venta"),
                                fieldWithPath("cliente.id").ignored(),
                                fieldWithPath("cliente.nombre").ignored(),
                                fieldWithPath("cliente.correoContacto").ignored(),
                                fieldWithPath("cliente.numeroEmpleados").ignored(),
                                fieldWithPath("cliente.direccion").ignored(),
                                fieldWithPath("productos[]").description("Productos comprados"),
                                fieldWithPath("productos[].id").ignored(),
                                fieldWithPath("productos[].nombre").ignored(),
                                fieldWithPath("productos[].categoria").ignored(),
                                fieldWithPath("productos[].precio").ignored(),
                                fieldWithPath("productos[].numeroRegistro").ignored(),
                                fieldWithPath("productos[].fechaCreacion").ignored()
                              
                        ),
                        responseHeaders(
                                headerWithName("Location").description("La ubicación del recurso (su identificador generado")
                        ))
                );
    }

    @Test
    void actualizaVenta() throws Exception {

        ClienteEntity cliente2 = clienteMapper.clienteToClienteEntity(Cliente.builder().id(2L).nombre("Lola").correoContacto("pds.f@gmail.com").numeroEmpleados("30").direccion( "Leon 12").build());
        ProductoEntity producto1 = productoMapper.productoToProductoEntity(Producto.builder().id(1L).nombre("Ciencia").categoria("Libro").precio(900).numeroRegistro("212-422-2229").fechaCreacion(LocalDateTime.now()).build());
        ProductoEntity producto2 = productoMapper.productoToProductoEntity(Producto.builder().id(2L).nombre("Animacion").categoria("Serie").precio(100).numeroRegistro("212-422-2222").fechaCreacion(LocalDateTime.now()).build());
        List<ProductoEntity> productos = Arrays.asList(producto1, producto2);
        Venta ventaParametro = Venta.builder().ventaId(1L).monto(599f).cliente(cliente2).productos(productos).fechaCreacion(LocalDateTime.now()).build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        mockMvc.perform(put("/venta/{ventaId}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ventaParametro)))
                .andExpect(status().isNoContent())

                .andDo(document("venta/put-venta",
                        pathParameters(
                                parameterWithName("ventaId").description("Identificador de la venta")
                        ),
                        requestFields(
                                fieldWithPath("ventaId").description("identificador de la venta"),
                                fieldWithPath("monto").description("monto total de la venta"),
                                fieldWithPath("fechaCreacion").description("Fecha en la que se realizó la venta"),
                                fieldWithPath("cliente").description("cliente que realizó la venta"),
                                fieldWithPath("cliente.id").ignored(),
                                fieldWithPath("cliente.nombre").ignored(),
                                fieldWithPath("cliente.correoContacto").ignored(),
                                fieldWithPath("cliente.numeroEmpleados").ignored(),
                                fieldWithPath("cliente.direccion").ignored(),
                                fieldWithPath("productos[]").description("Productos comprados"),
                                fieldWithPath("productos[].id").ignored(),
                                fieldWithPath("productos[].nombre").ignored(),
                                fieldWithPath("productos[].categoria").ignored(),
                                fieldWithPath("productos[].precio").ignored(),
                                fieldWithPath("productos[].numeroRegistro").ignored(),
                                fieldWithPath("productos[].fechaCreacion").ignored()
                          )
                ));
    }

    @Test
    void eliminaEtapa() throws Exception {
        mockMvc.perform(delete("/venta/{ventaId}",1)
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andDo(document("venta/delete-venta",
                        pathParameters(
                                parameterWithName("ventaId").description("Identificador de la venta")
                        )));
    }
    
}
