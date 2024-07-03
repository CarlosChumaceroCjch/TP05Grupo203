package ar.edu.unju.fi.model;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name="codigomateria")
    private Long codigo;
	@NotNull
	@Column(name="nombremateria")
    private String nombre;
	@NotNull
	@Column(name="cursomateria")
    private int curso;
	@NotNull
	@Column(name="horasmateria")
    private int cantHoras;
	@NotNull
	@Column(name="modalidadmateria")
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