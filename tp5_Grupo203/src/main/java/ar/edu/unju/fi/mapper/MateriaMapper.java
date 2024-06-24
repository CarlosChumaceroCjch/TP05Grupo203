package ar.edu.unju.fi.mapper;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.model.Materia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class MateriaMapper {
	MateriaMapper INSTANCE = Mappers.getMapper(MateriaMapper.class);

    MateriaDTO toMateriaDTO(Materia materia);
    Materia toMateria(MateriaDTO materiaDTO);
}
