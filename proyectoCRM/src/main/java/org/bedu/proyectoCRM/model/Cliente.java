package org.bedu.proyectoCRM.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class Cliente {
    
    @PositiveOrZero
    private Long id;

    @NotEmpty
    @Size(min = 3, max = 30)
    private String nombre;

    @Email
    private String correoContacto;

    @Min(value = 20)
    @Max(value = 10000, message = "Los clientes con más de 10000 empleados no son válidos")
    private String numeroEmpleados;

    @Column(nullable = false, length = 35)
    @NotBlank(message = "Se debe proporcionar una dirección")
    private String direccion;
}
