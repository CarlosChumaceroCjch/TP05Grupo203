package ar.edu.unju.fi.model;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"carrera"})
public class Materia {
	@Id
    private Long codigo;
    private String nombre;
    private int curso;
    private int cantHoras;
    private String modalidad;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="legajo")
    private Docente docente;
    //Estado para filtrar y borrado logico
    private Boolean estado;
    //Tp5parte2
    @ManyToMany(mappedBy="Materias")
    private List<Alumno> alumnos;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cod")
    private Carrera carrera;
}