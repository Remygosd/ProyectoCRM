package org.bedu.proyectoCRM.controller.mappers;

import org.bedu.proyectoCRM.model.Etapa;
import org.bedu.proyectoCRM.persistence.entities.EtapaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EtapaMapper {
    
    EtapaEntity etapaModelToEtapaEntity(Etapa etapa);

    Etapa etapaEntityToEtapaModel(EtapaEntity etapaEntity);
}
