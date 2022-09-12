package org.bedu.proyectoCRM.persistence;

import org.bedu.proyectoCRM.persistence.entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<VentaEntity, Long>{
    
}
