package org.bedu.proyectoCRM.runners;


import java.util.Arrays;
import java.util.List;

import org.bedu.proyectoCRM.controller.mappers.EtapaMapper;
import org.bedu.proyectoCRM.model.Etapa;
import org.bedu.proyectoCRM.persistence.EtapaRepository;
import org.bedu.proyectoCRM.persistence.entities.EtapaEntity;

import org.mapstruct.factory.Mappers;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EtapaVentaRunner implements CommandLineRunner{

    private final EtapaRepository etapaRepository;
    private EtapaMapper etapaMapper = Mappers.getMapper(EtapaMapper.class);

    @Override
    public void run(String... args) throws Exception {
        EtapaEntity etapa1 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("En espera").orden(0).build());
        EtapaEntity etapa2 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Reunión de exploración").orden(1).build());
        EtapaEntity etapa3 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Metas establecidas").orden(2).build());
        EtapaEntity etapa4 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Plan de acción presentado").orden(3).build());
        EtapaEntity etapa5 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Contrato firmado").orden(4).build());
        EtapaEntity etapa6 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Venta ganada").orden(5).build());
        EtapaEntity etapa7 = etapaMapper.etapaModelToEtapaEntity(Etapa.builder().nombre("Venta perdida").orden(6).build());

        List<EtapaEntity> etapas = Arrays.asList(etapa1, etapa2, etapa3, etapa4, etapa5, etapa6, etapa7);

        etapaRepository.saveAll(etapas);
    
 }
}