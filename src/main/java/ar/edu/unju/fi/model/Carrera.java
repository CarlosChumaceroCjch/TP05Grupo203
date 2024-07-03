package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Component
@Entity
public class Carrera {
	
	@Id
	private String cod;
	private String nombre;
	private Integer cantAnios;
	private Boolean status;
	@OneToMany(mappedBy="carrera",cascade = CascadeType.ALL)
	private List<Alumno> alumnos;
	@OneToMany(mappedBy = "carrera",cascade = CascadeType.ALL )
	private List<Materia> materias;
}
