package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class Materia{
	
	@Id
    private Long codigo;
	@Size(min = 3, max = 30, message = "Este campo debe tener entre 3 y 30 caracteres.")
	@Pattern(regexp = "[a-z A-Z]*", message = "Este campo solo debe contener Letras.")
    private String nombre;
	@Min(value=0,message="Debe ser mayor a 0")
    private int curso;
	@Min(value=5,message="indicar mayor a 3")
    private int cantHoras;
	@NotNull
    private String modalidad;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="legajo",referencedColumnName="legajo")
    private Docente docente;
    private Boolean estado;
    //Tp5parte2
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Alumno> alumnos;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cod")
    private Carrera carrera;
}