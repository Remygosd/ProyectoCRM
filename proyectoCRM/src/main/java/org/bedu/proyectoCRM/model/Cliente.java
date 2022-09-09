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
    
    @PositiveOrZero(message = "El identificador no puede ser un número negativo")
    private long id;

    @NotEmpty(message = "El nombre del cliente no puede estar vacío")
    @Size(min = 5, max = 30, message = "El nombre del cliente debe tener al menos 5 letras y ser menor a 30")
    private String nombre;

    @Email
    private String correoContacto;

    @Min(value = 10, message = "Los clientes con menos de 10 empleados no son válidos")
    @Max(value = 10000, message = "Los clientes con más de 10000 empleados no son válidos")
    private String numeroEmpleados;

    @Column(nullable = false, length = 35)
    @NotBlank(message = "Se debe proporcionar una dirección")
    private String direccion;
}