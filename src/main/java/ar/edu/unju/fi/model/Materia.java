package ar.edu.unju.fi.model;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia {
	@Id
    private Long codigo;
    private String nombre;
    private int curso;
    private int cantHoras;
    private String modalidad;
   // private Docente docente;
   // private Carrera carrera;
    //Estado para filtrar y borrado logico
    private Boolean estado;
}