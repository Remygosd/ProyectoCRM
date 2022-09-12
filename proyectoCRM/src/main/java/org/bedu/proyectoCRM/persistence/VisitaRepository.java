package org.bedu.proyectoCRM.persistence;

import org.bedu.proyectoCRM.persistence.entities.VisitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitaRepository extends JpaRepository<VisitaEntity, Long> {
}