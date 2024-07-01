package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	//@ManyToMany(mappedBy="alumnos")
	//private List<Materia> Materias;
}



