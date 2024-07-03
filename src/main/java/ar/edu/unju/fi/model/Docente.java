package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Component
@Entity
public class Docente {
	@Id
	@NotNull
	@Column(name="legajo")
	private String legajo;
	@NotNull
	@Column(name="nombredocente")
	private String nombre;
	@NotNull
	@Column(name="apellidodocente")
	private String apellido;
	@NotNull
	@Email
	@Column(name="emaildocente")
	private String email;
	@NotNull
	@Column(name="telefonodocente")
	private String telefono;
	@NotNull
	@Column(name="estadodocente")
	private boolean estado;

	

}
