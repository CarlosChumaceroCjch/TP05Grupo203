package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaServiceImp implements MateriaService{
	@Autowired
    MateriaRepository materiaRepository;

    @Autowired
    MateriaMapDTO materiaMapDTO;

    @Override
    public List<Materia> listar() {
    	return materiaRepository.findMateriaByEstado(true);
		/*
		 * List<Materia> materias = materiaRepository.findAll(); return
		 * materias.stream() .map(MateriaMapDTO::toMateriaDTO)
		 * .collect(Collectors.toList());
		 */
    }

    @Override
    public void guardar(MateriaDTO materiaDTO) {
    	materiaRepository.save(materiaMapDTO.convertirMateriaDTOAMateria(materiaDTO));
		/*
		 * Materia materia = materiaMapDTO.toMateria(materiaDTO); materia =
		 * materiaRepository.save(materia); return materiaMapper.toMateriaDTO(materia);
		 */
    }

    @Override
    public MateriaDTO obtenerPorId(Long id) {
        Materia materia = materiaRepository.findById(id).orElse(null);
        return materia != null ? materiaMapDTO.convertirMateriaAMateriaDTO(materia) : null;
    }

    @Override
    public void eliminar(Long id) {
    	List<Materia>listadoMaterias = materiaRepository.findAll();
    	//Borrado logico
    	for (Materia m: listadoMaterias) {
    		if(m.getCodigo().equals(id)) {
    			m.setEstado(false);
    			materiaRepository.save(m);
    			break;
    		}
    	}
        //materiaRepository.deleteById(id);
    }

	@Override
	public void modificarMateria(MateriaDTO mDTO) {
		List<Materia>listadoMaterias = materiaRepository.findAll();
    	for (Materia m: listadoMaterias) {
    		if(m.getCodigo().equals(mDTO.getCodigo())) {
    			m.setEstado(false);
    			materiaRepository.save(materiaMapDTO.convertirMateriaDTOAMateria(mDTO));
    			break;
    		}
    	}
	}
}
