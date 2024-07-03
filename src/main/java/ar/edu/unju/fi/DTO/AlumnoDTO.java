package ar.edu.unju.fi.DTO;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
@Data
public class AlumnoDTO {
	private String lu;
	private Integer dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private LocalDate fechaNacimiento;
	private String domicilio;
	private Boolean estado;
	private Carrera carrera;
}
