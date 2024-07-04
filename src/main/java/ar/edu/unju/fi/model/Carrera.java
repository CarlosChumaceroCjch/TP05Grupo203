package ar.edu.unju.fi.model;


import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Component
@Entity
public class Carrera{
	
	@Id
	@NotNull
	private String cod;
	@Size(min=3, max=100, message="Este campo debe tener entre entre 3 y 100 caracteres.")
	@Pattern(regexp = "[a-z A-Z]*", message = "Este campo solo debe contener Letras.")
	private String nombre;
	@Min(value=3,message="Duracion de la carrera mayor a 3")
	@Max(value=6,message="Debe ser menor a 6")
	private Integer cantAnios;
	@NotNull
	private Boolean status;
	@OneToMany(mappedBy="carrera",cascade = CascadeType.ALL)
	private List<Alumno> alumnos;
	@OneToMany(mappedBy = "carrera",cascade = CascadeType.ALL )
	private List<Materia> materias;
}
