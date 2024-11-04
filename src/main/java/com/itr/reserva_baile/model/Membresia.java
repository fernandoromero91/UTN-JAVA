package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("membresia")
public class Membresia {
    @Id
    @Column("id_membresia")
    private Long idMembresia;

    @NotNull(message = "El usuarioId es obligatorio")
    @Column("id_usuario")
    private Long idUsuario;

    @NotEmpty(message = "El Nombre de Membresia es obligatorio")
    private String nombreMembresia; // Ej. "confirmada", "cancelada", "modificada"

	private Long beneficio; // Este atributo es opcional

    @NotNull(message = "El descuento es obligatorio")
    @Positive(message = "El descuento debe ser positivo")
    private Double descuento;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser positivo")
    private Double precio;

    @NotEmpty(message = "La fecha Inicial es obligatoria")
    private String fechaInicio; // fecha_inicio de la Membresia

    @NotEmpty(message = "La fecha Final es obligatoria")
    private String fechaFin; // fecha_fin de la Membresia

}
