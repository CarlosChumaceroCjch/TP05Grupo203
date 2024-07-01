package ar.edu.unju.fi.map;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MateriaMapDTO {
	@Mapping(source="codigo", target = "codigo")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "curso", target = "curso")
	@Mapping(source = "cantHoras", target = "cantHoras")
	@Mapping(source = "modalidad", target = "modalidad")
	@Mapping(source = "docente", target = "docente")
	@Mapping(source = "carrera", target = "carrera")
	@Mapping(source = "estado", target = "estado")
	
	
	MateriaDTO convertirMateriaAMateriaDTO(Materia m);
	@InheritInverseConfiguration
	Materia convertirMateriaDTOAMateria(MateriaDTO m);
	
	List<MateriaDTO> convertirListaMateriasAListaMateriasDTO (List<Materia> listaM);
	List<Materia> convertirListaMateriasDTOAListaMaterias (List<MateriaDTO> listaMDTO);
	
	/*
	 * @Mapper public class MateriaMapper { MateriaMapper INSTANCE =
	 * Mappers.getMapper(MateriaMapper.class);
	 * 
	 * MateriaDTO toMateriaDTO(Materia materia); Materia toMateria(MateriaDTO
	 * materiaDTO); }
	 */
}
