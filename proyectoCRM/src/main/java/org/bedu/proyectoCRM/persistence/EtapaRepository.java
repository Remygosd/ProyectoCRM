package org.bedu.proyectoCRM.persistence;

import org.bedu.proyectoCRM.persistence.entities.EtapaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapaRepository extends JpaRepository<EtapaEntity, Long>{
    
}
