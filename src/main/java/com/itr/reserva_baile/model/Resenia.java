package com.itr.reserva_baile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("resenia")
public class Resenia {
    @Id
    @Column("id_membresia")
    private Long idResenia;

    @NotNull(message = "El usuarioId es obligatorio")
    @Column("id_usuario")
    private Long idUsuario;

    private Long idClase; //El valor es Opcional

    private Long idEstudio;  //El valor es Opcional
	
	@NotNull(message = "El puntuacion es obligatorio")
    @Column("puntuacion")
    private String puntuacion; // Ej. "1", "2", "5"
	
	private Long comentario; // Este atributo es opcional
	
    @NotEmpty(message = "La fecha es obligatoria")
    @Column("fecha")
    private String fecha; // fecha de la reserva
}
