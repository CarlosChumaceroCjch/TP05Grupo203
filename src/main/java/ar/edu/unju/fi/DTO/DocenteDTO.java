package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class DocenteDTO {
	private String legajo;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private boolean estado;

}
