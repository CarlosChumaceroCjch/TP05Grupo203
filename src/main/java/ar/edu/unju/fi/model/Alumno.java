package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	
	@Column(name="lu")
	private String lu;
	@Column(name="dni")
	private Integer dni;
	@Column(name="nombrealumno")
	private String nombre;
	@Column(name="apellidoalumno")
	private String apellido;
	@Column(name="emailalumno")
	private String email;
	@Column(name="telefonoalumno")
	private String telefono;
	@Column(name="nacimientoalumno")
	private LocalDate fechaNacimiento;
	@Column(name="domicilioalumno")
	private String domicilio;
	@Column(name="estadoalumno")
	private Boolean estado;
	//tp5Parte2
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Materia> Materias;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cod")
	private Carrera carrera;
}



