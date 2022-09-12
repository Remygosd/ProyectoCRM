package org.bedu.proyectoCRM.controller.mappers;

import org.bedu.proyectoCRM.model.Venta;
import org.bedu.proyectoCRM.persistence.entities.VentaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VentaMapper {
    VentaEntity ventaModelToVentaEntity(Venta ventaModel);

  Venta ventaEntityToVentaModel(VentaEntity venta);
}
