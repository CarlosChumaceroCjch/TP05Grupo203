package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Component
@Entity
public class Docente {
	@Id
	@Column(name="legajo")
	private String legajo;
	@Column(name="nombredocente")
	private String nombre;
	@Column(name="apellidodocente")
	private String apellido;
	@Column(name="emaildocente")
	private String email;
	@Column(name="telefonodocente")
	private String telefono;
	@Column(name="estadodocente")
	private boolean estado;

	

}
