package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlumnoMapDTO {
	@Mapping (source="lu", target="lu")
	@Mapping (source="dni", target="dni")
	@Mapping (source="nombre", target="nombre")
	@Mapping (source="apellido", target="apellido")
	@Mapping (source="email", target="email")
	@Mapping (source="telefono", target="telefono")
	@Mapping (source="fechaNacimiento", target="fechaNacimiento")
	@Mapping (source="domicilio", target="domicilio")
	@Mapping (source="estado", target="estado")
	@Mapping (source="carrera", target="carrera")
	AlumnoDTO convertirAlumnoAAlumnoDto (Alumno alumno);
	

	@InheritInverseConfiguration
	Alumno convertirDTOaAlumno(AlumnoDTO alumnoDto);
	
	List<AlumnoDTO> convertirListaAlumnosAListaAlumnosDTO(List<Alumno> listaAlumno);
	List<Alumno> convertirListaAlumnosDTOAListaAlumnos(List<AlumnoDTO> listaAlumnoDto);
}
