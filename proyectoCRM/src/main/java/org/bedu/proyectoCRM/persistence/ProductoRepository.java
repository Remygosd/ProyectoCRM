package org.bedu.proyectoCRM.persistence;

import org.bedu.proyectoCRM.persistence.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
