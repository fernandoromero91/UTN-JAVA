package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("paquete")
public class Paquete {
    @Id
    private Long id;

    @NotEmpty(message = "El nombre del paquete es obligatorio")
    private String nombrePaquete;

    @NotEmpty(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", message = "El precio debe ser mayor o igual a 0")
    private BigDecimal precio;

    @NotNull(message = "La duración es obligatoria")
    private Integer duracion;

    // Como es una relación con otra entidad, no se incluye la validación aquí
    // Se manejará a través de la tabla intermedia
    private List<ClaseDeBaile> clasesIncluidas;
}