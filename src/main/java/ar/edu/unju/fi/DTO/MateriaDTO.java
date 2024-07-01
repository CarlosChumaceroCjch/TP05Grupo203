package ar.edu.unju.fi.DTO;

import lombok.Data;
import ar.edu.unju.fi.DTO.DocenteDTO;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.DTO.CarreraDTO;

@Component
@Data
public class MateriaDTO {
	private Long codigo;
    private String nombre;
    private int curso;
    private int cantHoras;
    private String modalidad;
    private DocenteDTO docente;
    private CarreraDTO carrera;
    private Boolean estado;
}
