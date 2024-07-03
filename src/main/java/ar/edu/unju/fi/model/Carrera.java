package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Component
@Entity
public class Carrera {
	
	@Id
	
	@Column(name="codigocarrera")
	private String cod;
	@Column(name="nombrecarrera")
	private String nombre;
	@Column(name="anioscarrera")
	private Integer cantAnios;
	@Column(name="statuscarrera")
	private Boolean status;
	@OneToMany(mappedBy="carrera",cascade = CascadeType.ALL)
	private List<Alumno> alumnos;
	@OneToMany(mappedBy = "carrera",cascade = CascadeType.ALL )
	private List<Materia> materias;
}
