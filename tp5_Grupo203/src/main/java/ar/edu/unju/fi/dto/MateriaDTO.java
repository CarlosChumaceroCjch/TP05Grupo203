package ar.edu.unju.fi.dto;

import lombok.Data;
import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.dto.CarreraDTO;

@Data
public class MateriaDTO {
	private Long codigo;
    private String nombre;
    private int curso;
    private int cantHoras;
    private String modalidad;
    private DocenteDTO docente;
    private CarreraDTO carrera;
}
