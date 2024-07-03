package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Component
@Entity
public class Alumno {
	
	@Id
	private String lu;
	private Integer dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private LocalDate fechaNacimiento;
	private String domicilio;
	private Boolean estado;
	//tp5Parte2
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Materia> Materias;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cod")
	private Carrera carrera;
}



