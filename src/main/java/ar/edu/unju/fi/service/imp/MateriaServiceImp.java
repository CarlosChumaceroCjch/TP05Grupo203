package ar.edu.unju.fi.service.imp;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@Service
public class MateriaServiceImp implements MateriaService{
	private static final Logger logger = LoggerFactory.getLogger(MateriaServiceImp.class);
	@Autowired
    MateriaRepository materiaRepository;

    @Autowired
    MateriaMapDTO materiaMapDTO;

    @Override
    public List<MateriaDTO> listar() {
    	logger.info("Listando materias con estado verdadero");
        List<MateriaDTO> materiasDTO = materiaMapDTO.convertirListaMateriasAListaMateriasDTO(materiaRepository.findMateriaByEstado(true));
        logger.info("Materias listadas: {}", materiasDTO);
        return materiasDTO;
    }

    @Override
    public void guardar(Materia materia) {
    	logger.info("Guardando materia: {}", materia);
    	materiaRepository.save(materia);
    }

    @Override
    public MateriaDTO obtenerPorId(Long id) {
    	logger.info("Obteniendo materia por ID: {}", id);
        Materia materia = materiaRepository.findById(id).orElse(null);
        MateriaDTO materiaDTO = materia != null ? materiaMapDTO.convertirMateriaAMateriaDTO(materia) : null;
        logger.info("Materia obtenida: {}", materiaDTO);
        return materiaDTO;
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
    	logger.info("Eliminando (borrado lógico) materia por ID: {}", id);
        Materia materia = materiaRepository.findById(id).orElse(null);
        if (materia != null) {
            materia.setEstado(false);
            materiaRepository.save(materia);
            logger.info("Materia eliminada: {}", materia);
        } else {
            logger.warn("Materia no encontrada para el ID: {}", id);
        }
    }

	@Override
	@Transactional
	public void modificarMateria(Materia m) {
		logger.info("Modificando materia: {}", m);
        Materia materia = materiaRepository.findById(m.getCodigo()).orElse(null);
        if (materia != null) {
        	materia.setCodigo(m.getCodigo());
        	materia.setNombre(m.getNombre());
        	materia.setCurso(m.getCurso());
        	materia.setCantHoras(m.getCantHoras());
        	materia.setModalidad(m.getModalidad());
            materia.setEstado(m.getEstado());
            materia.setDocente(m.getDocente());
            materia.setCarrera(m.getCarrera());
            materia.setAlumnos(m.getAlumnos());
            materiaRepository.save(materia);
            logger.info("Materia modificada: {}", materia);
        	
        }else {
            logger.warn("Materia no encontrada para el ID: {}", m.getCodigo());
        }

	}
         
}
