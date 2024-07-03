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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;


@Data
@Component
@Entity
public class Alumno {
	
	@Id
	@NotNull
	@Column(name="lu")
	private String lu;
	@NotNull
	@Column(name="dni")
	private Integer dni;
	@NotNull
	@Column(name="nombrealumno")
	private String nombre;
	@NotNull
	@Column(name="apellidoalumno")
	private String apellido;
	@NotNull
	@Email
	@Column(name="emailalumno")
	private String email;
	@NotNull
	@Column(name="telefonoalumno")
	private String telefono;
	@NotNull
	@Past
	@Column(name="nacimientoalumno")
	private LocalDate fechaNacimiento;
	@NotNull
	@Column(name="domicilioalumno")
	private String domicilio;
	@NotNull
	@Column(name="estadoalumno")
	private Boolean estado;
	//tp5Parte2
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Materia> Materias;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cod")
	private Carrera carrera;
}



