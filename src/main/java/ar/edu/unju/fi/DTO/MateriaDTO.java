package ar.edu.unju.fi.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@Data
public class MateriaDTO {
	private Long codigo;
    private String nombre;
    private int curso;
    private int cantHoras;
    private String modalidad;
    private Docente docente;
    private Carrera carrera;
    private List<Alumno> alumnos;
    private Boolean estado;
}
