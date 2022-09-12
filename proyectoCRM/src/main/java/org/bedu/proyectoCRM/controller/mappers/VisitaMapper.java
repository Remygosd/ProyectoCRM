package org.bedu.proyectoCRM.controller.mappers;

import org.bedu.proyectoCRM.model.Visita;
import org.bedu.proyectoCRM.persistence.entities.VisitaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitaMapper {
    Visita visitaEntityToVisita(VisitaEntity visitaEntity);

    VisitaEntity visitaToVisitaEntity(Visita visita);
}