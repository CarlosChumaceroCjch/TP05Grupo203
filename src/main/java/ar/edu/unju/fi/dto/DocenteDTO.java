package ar.edu.unju.fi.dto;

import org.springframework.stereotype.Component;

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
	private int telefono;
	private boolean estado;

}