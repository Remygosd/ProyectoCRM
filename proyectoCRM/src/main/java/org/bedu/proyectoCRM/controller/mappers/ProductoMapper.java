package org.bedu.proyectoCRM.controller.mappers;

import org.bedu.proyectoCRM.model.Producto;
import org.bedu.proyectoCRM.persistence.entities.ProductoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ProductoMapper {
    Producto productoEntityToProducto(ProductoEntity productoEntity);

    ProductoEntity productoToProductoEntity( Producto producto);

}

