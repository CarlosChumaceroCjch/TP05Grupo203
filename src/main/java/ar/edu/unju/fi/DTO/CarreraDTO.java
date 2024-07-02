package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class CarreraDTO {
	private String nombre;
	private Integer cantAnios;
	private String cod;
	private Boolean status;
	
}
