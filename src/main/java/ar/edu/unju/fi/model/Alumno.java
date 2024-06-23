package ar.edu.unju.fi.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;


@Data
@Component
@Entity
public class Alumno {
	
	@Id
	private String lu;
	@NonNull
	private Integer dni;
	@NonNull
	private String nombre;
	@NonNull
	private String apellido;
	@NonNull
	private String email;
	@NonNull
	private String telefono;
	@NonNull
	private LocalDate fechaNacimiento;
	@NonNull
	private String domicilio;
	@NonNull
	private Boolean estado;
	
	public String getLu() {
		return lu;
	}
	public void setLu(String lu) {
		this.lu = lu;
	}
	
	
}



