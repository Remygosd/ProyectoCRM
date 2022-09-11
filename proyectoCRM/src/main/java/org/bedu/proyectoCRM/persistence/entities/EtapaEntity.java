package org.bedu.proyectoCRM.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ETAPA")
@NoArgsConstructor
public class EtapaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long etapaId;

   
    private String nombre;

    @Column(unique = true, nullable = false)
    private int orden;


    
}
