package ar.edu.unju.fi.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class CarreraDTO {
	private String nombre;
	private Integer cantAnios;
	private String cod;
	private Boolean status;
	private List<Alumno> alumnos;
	private List<Materia> materias;
	
}
