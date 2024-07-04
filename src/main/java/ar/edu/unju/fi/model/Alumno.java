package ar.edu.unju.fi.model;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Component
@Entity
public class Alumno{

	@Id
	@Size(min=4, max=8,message="longitud de LU no valida")
	private String lu;
	@Min(value=10000000, message="Dni no valido")
	@Max(value=99999999,message="Dni no valido")
	private Integer dni;
	@Size(min=3,message="El nombre debe contener como minimo 3 Caracteres como minimo y 20 como maximo")
	@Pattern(regexp="[a-z A-Z]*",message="Nombre no valido")
	private String nombre;
	@Size(min=3, max=20,message="El nombre debe contener como minimo 3 Caracteres como minimo y 20 como maximo")
	@Pattern(regexp="[a-z A-Z]*",message="Apellido no valido")
	private String apellido;
	@Email
	private String email;
	@Size(min=9, max=10,message="longitud no valida")
	@Pattern(regexp="[0-9]*",message="Solo se debe ingresar Numeros")
	private String telefono;
	@Past
	@NotNull
	private LocalDate fechaNacimiento;
	@Size(min=8, max=30,message="longitud del Domicilio no valida")
	private String domicilio;
	private Boolean estado;
	//tp5Parte2
	@ManyToMany(mappedBy="alumnos")
	private List<Materia> Materias;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cod")
	private Carrera carrera;
}



