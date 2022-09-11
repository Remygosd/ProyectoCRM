package org.bedu.proyectoCRM.persistence;

import org.bedu.proyectoCRM.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository  extends JpaRepository<ClienteEntity, Long> {
    
}
