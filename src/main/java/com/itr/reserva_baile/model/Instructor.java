package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("instructor")
public class Instructor {
    @Id
    private Long id;

    @NotEmpty(message = "El nombre es obligatorio")
    private String nombre;

    @NotEmpty(message = "La especialidad es obligatoria")
    private String especialidad;

    @NotNull(message = "La experiencia es obligatoria")
    @Positive(message = "La experiencia debe ser un número positivo")
    private Integer experiencia;

    @NotEmpty(message = "El contacto es obligatorio")
    private String contacto;
}
