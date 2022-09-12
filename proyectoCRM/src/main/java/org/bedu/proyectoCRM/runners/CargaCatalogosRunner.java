package org.bedu.proyectoCRM.runners;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.bedu.proyectoCRM.controller.mappers.ClienteMapper;
import org.bedu.proyectoCRM.controller.mappers.EtapaMapper;
import org.bedu.proyectoCRM.controller.mappers.ProductoMapper;
import org.bedu.proyectoCRM.controller.mappers.VentaMapper;
import org.bedu.proyectoCRM.controller.mappers.VisitaMapper;
import org.bedu.proyectoCRM.model.Cliente;
import org.bedu.proyectoCRM.model.Etapa;
import org.bedu.proyectoCRM.model.Producto;
import org.bedu.proyectoCRM.model.Venta;
import org.bedu.proyectoCRM.model.Visita;
import org.bedu.proyectoCRM.persistence.ClienteRepository;
import org.bedu.proyectoCRM.persistence.EtapaRepository;
import org.bedu.proyectoCRM.persistence.ProductoRepository;
import org.bedu.proyectoCRM.persistence.VentaRepository;
import org.bedu.proyectoCRM.persistence.VisitaRepository;
import org.bedu.proyectoCRM.persistence.entities.ClienteEntity;
import org.bedu.proyectoCRM.persistence.entities.EtapaEntity;
import org.bedu.proyectoCRM.persistence.entities.ProductoEntity;
import org.bedu.proyectoCRM.persistence.entities.VentaEntity;
import org.bedu.proyectoCRM.persistence.entities.VisitaEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CargaCatalogosRunner implements CommandLineRunner{

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
private ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);
    private VentaMapper ventaMapper = Mappers.getMapper(VentaMapper.class);
    private final EtapaRepository etapaRepository;
    private EtapaMapper etapaMapper = Mappers.getMapper(EtapaMapper.class);

    private VisitaMapper visitaMapper = Mappers.getMapper(VisitaMapper.class);
    private final VisitaRepository visitaRepository;
    private final ProductoRepository productoRepository;

    private ProductoMapper productoMapper = Mappers.getMapper(ProductoMapper.class);

    @Override
    public void run(String... args) throws Exception {
        ClienteEntity cliente1 = clienteMapper.clienteToClienteEntity(Cliente.builder().nombre("Fanny").correoContacto("fanny.f@gmail.com").numeroEmpleados("200").direccion( "Anahuac211").build());
        ClienteEntity cliente2 = clienteMapper.clienteToClienteEntity(Cliente.builder().nombre("Lola").correoContacto("pds.f@gmail.com").numeroEmpleados("30").direccion( "Leon 12").build());
        ClienteEntity cliente3 = clienteMapper.clienteToClienteEntity(Cliente.builder().nombre("Nestor").correoContacto("nestor.f@gmail.com").numeroEmpleados("100").direccion(  " Madrid 91").build());
    

        List<ClienteEntity> cliente = Arrays.asList(cliente1, cliente2, cliente3);
        

        cliente = clienteRepository.saveAll(cliente);  
        System.out.println(cliente);

        VisitaEntity visita1 = visitaMapper.visitaToVisitaEntity(Visita.builder().cliente(clienteRepository.findById(1L).get()).fechaProgramada(LocalDateTime.now()).direccion("Nepomuceno 124").proposito("Visitar las playas blancas").vendedor("D'Artagnan").build());
        VisitaEntity visita2 = visitaMapper.visitaToVisitaEntity(Visita.builder().cliente(clienteRepository.findById(3L).get()).fechaProgramada(LocalDateTime.now()).direccion("Esmeralda 1024").proposito("Realizar deportes extremos").vendedor("Athos").build());
        VisitaEntity visita3 = visitaMapper.visitaToVisitaEntity(Visita.builder().cliente(clienteRepository.findById(2L).get()).fechaProgramada(LocalDateTime.now()).direccion("Nabuconosor 32").proposito("Conocer y ampliar horizontes").vendedor("Porthos").build());


        List<VisitaEntity> visita = Arrays.asList(visita1, visita2, visita3);

         
         System.out.println(visitaRepository.saveAll(visita));


        ProductoEntity producto1 = productoMapper.productoToProductoEntity(Producto.builder().nombre("Ciencia").categoria("Libro").precio(900).numeroRegistro("212-422-2229").fechaCreacion(LocalDateTime.now()).build());
        ProductoEntity producto2 = productoMapper.productoToProductoEntity(Producto.builder().nombre("Animacion").categoria("Serie").precio(100).numeroRegistro("212-422-2222").fechaCreacion(LocalDateTime.now()).build());
        ProductoEntity producto3 = productoMapper.productoToProductoEntity(Producto.builder().nombre("La Jefa").categoria("Novela").precio(400).numeroRegistro("212-422-2282").fechaCreacion(LocalDateTime.now()).build());
        ProductoEntity producto4 = productoMapper.productoToProductoEntity(Producto.builder().nombre("Brawll").categoria("Videojuego").precio(500).numeroRegistro("212-422-2622").fechaCreacion(LocalDateTime.now()).build());
        ProductoEntity producto5 = productoMapper.productoToProductoEntity(Producto.builder().nombre("Toy's story").categoria("Película").precio(960).numeroRegistro("212-422-2223").fechaCreacion(LocalDateTime.now()).build());
        ProductoEntity producto6 = productoMapper.productoToProductoEntity(Producto.builder().nombre("Romance").categoria("Libro").precio(500).numeroRegistro("214-422-2222").fechaCreacion(LocalDateTime.now()).build());

        List<ProductoEntity> productos = Arrays.asList(producto1, producto2, producto3, producto4, producto5, producto6);
        
        productos = productoRepository.saveAll(productos);
      
        System.out.println(productos);
       

        VentaEntity venta1 = ventaMapper.ventaModelToVentaEntity(Venta.builder().monto(599f).cliente(clienteRepository.findById(1L).get()).productos(productos).fechaCreacion(LocalDateTime.now()).build());
        VentaEntity venta2 = ventaMapper.ventaModelToVentaEntity(Venta.builder().monto(700.5f).cliente(clienteRepository.findById(2L).get()).productos(productos).fechaCreacion(LocalDateTime.now()).build());
        VentaEntity venta3 = ventaMapper.ventaModelToVentaEntity(Venta.builder().monto(800.5f).cliente(clienteRepository.findById(3L).get()).productos(productos).fechaCreacion(LocalDateTime.now()).build());
        VentaEntity venta4 = ventaMapper.ventaModelToVentaEntity(Venta.builder().monto(900.50f).cliente(clienteRepository.findById(1L).get()).productos(productos).fechaCreacion(LocalDateTime.now()).build());
        VentaEntity venta5 = ventaMapper.ventaModelToVentaEntity(Venta.builder().monto(599f).cliente(clienteRepository.findById(1L).get()).productos(productos).fechaCreacion(LocalDateTime.now()).build());

        List<VentaEntity> ventas = Arrays.asList(venta1, venta2, venta3, venta4, venta5);

       
        System.out.println( ventaRepository.saveAll(ventas));

        EtapaEntity etapa1 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("En espera").orden(0).build());
        EtapaEntity etapa2 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Reunión de exploración").orden(1).build());
        EtapaEntity etapa3 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Metas establecidas").orden(2).build());
        EtapaEntity etapa4 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Plan de acción presentado").orden(3).build());
        EtapaEntity etapa5 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Contrato firmado").orden(4).build());
        EtapaEntity etapa6 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Venta ganada").orden(5).build());
        EtapaEntity etapa7 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Venta perdida").orden(6).build());

        List<EtapaEntity> etapas = Arrays.asList(etapa1, etapa2, etapa3, etapa4, etapa5, etapa6, etapa7);

        
        System.out.println(etapaRepository.saveAll(etapas));
    
        
    }

  



    
}
