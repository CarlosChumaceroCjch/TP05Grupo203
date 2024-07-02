package ar.edu.unju.fi.DTO;

import lombok.Data;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;

import org.springframework.stereotype.Component;

@Component
@Data
public class MateriaDTO {
	private Long codigo;
    private String nombre;
    private int curso;
    private int cantHoras;
    private String modalidad;
    private Docente docente;
    private Carrera carrera;
    private Boolean estado;
}
