package com.itr.reserva_baile.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.itr.reserva_baile.model.Usuario;
import com.itr.reserva_baile.model.ClaseDeBaile;
import com.itr.reserva_baile.model.EstudioDeBaile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {
    private Long id;
    private String fecha;
    private String hora;
    private Integer duracion;
    private String estado;
    private Usuario usuario;
    private ClaseDeBaile clase;
    private EstudioDeBaile estudio;
}