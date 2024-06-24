package ar.edu.unju.fi.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia {
    private Long codigo;
    private String nombre;
    private int curso;
    private int cantHoras;
    private String modalidad;
    private Docente docente;
    private Carrera carrera;
}