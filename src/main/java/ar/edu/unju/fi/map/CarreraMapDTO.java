package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapDTO {
	
	
	@Mapping (source="nombre", target="nombre")
	@Mapping (source="cantAnios", target="cantAnios")
	@Mapping (source="cod", target="cod")
	@Mapping (source="status", target="status")
	@Mapping (source="alumnos", target="alumnos")
	@Mapping (source="materias", target="materias")
	CarreraDTO convertirCarreraACarreraDto (Carrera c);
	

	
	@InheritInverseConfiguration
	Carrera convertirDTOaCarrera(CarreraDTO cdto);
	
	List<CarreraDTO> convertirListaCarrerasAListaCarrerasDTO(List<Carrera> listaC);
	List<Carrera> convertirListaCarrerasDTOAListaCarreras(List<CarreraDTO> listaCdto);
}
