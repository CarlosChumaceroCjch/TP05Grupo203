package ar.edu.unju.fi.service.impl;

import ar.edu.unju.fi.dto.MateriaDTO;
import ar.edu.unju.fi.mapper.MateriaMapper;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaServiceImpl implements MateriaService{
	@Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriaMapper materiaMapper;

    @Override
    public List<MateriaDTO> listar() {
        List<Materia> materias = materiaRepository.findAll();
        return materias.stream()
                .map(materiaMapper::toMateriaDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MateriaDTO guardar(MateriaDTO materiaDTO) {
        Materia materia = materiaMapper.toMateria(materiaDTO);
        materia = materiaRepository.save(materia);
        return materiaMapper.toMateriaDTO(materia);
    }

    @Override
    public MateriaDTO obtenerPorId(Long id) {
        Materia materia = materiaRepository.findById(id).orElse(null);
        return materia != null ? materiaMapper.toMateriaDTO(materia) : null;
    }

    @Override
    public void eliminar(Long id) {
        materiaRepository.deleteById(id);
    }
}
