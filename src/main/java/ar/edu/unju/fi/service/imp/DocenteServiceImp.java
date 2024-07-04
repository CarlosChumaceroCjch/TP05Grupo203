package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService{
	private static final Logger logger = LoggerFactory.getLogger(DocenteServiceImp.class);

	@Autowired
	DocenteRepository docenteRepository;
	
	@Autowired
	DocenteMapDTO carreraMapDTO;

	@Override
	public void guardarDocente(Docente docente) {
		// TODO Auto-generated method stub
		docente.setEstado(true);
		logger.info("Guardando docente: {}", docente);
		docenteRepository.save(docente);
		
	}

	@Override
	public List<DocenteDTO> mostrarDocentesDTO() {
		List<DocenteDTO>docentesDTO =carreraMapDTO.convertirListaDocentesAListaDocentesDTO(docenteRepository.findDocenteByEstado(true));
		logger.info("Mostrando lista de docentes: {}", docentesDTO);
		return docentesDTO;
	}

	@Override
	public void borrarDocente(String legajo) {
		// TODO Auto-generated method stub
		List<Docente> docentes = docenteRepository.findAll();
		for (int i = 0; i < docentes.size(); i++) {
		      Docente docente = docentes.get(i);
		      if (docente.getLegajo().equals(legajo)) {
		        docente.setEstado(false);
		        logger.info("Borrando docente: {}", docente);
		        docenteRepository.save(docente);
		        break;
		      }
		    }
	}

	@Override
	public void modificarDocente(Docente docente) {
		// TODO Auto-generated method stub
		List<Docente> docentes=docenteRepository.findAll();
		docente.setEstado(true);
		for(int i=0;i<docentes.size();i++) {
			Docente docent = docentes.get(i);
			if(docent.getLegajo().equals(docente.getLegajo())) {
				logger.info("Modificando docente: {}", docente);
				//docentes.set(i, docenteDTO);
				docenteRepository.save(docente);
				break;
			}
		}
		
	}

	@Override
	public Docente buscarDocente(String legajo) {
		// TODO Auto-generated method stub
		List<Docente> docentes= docenteRepository.findAll();
		for (Docente d : docentes) {
			if (d.getLegajo().equals(legajo)) {
				logger.info("Docente encontrado: {}", d);
				return d;
			}		
		}
		 logger.info("Docente no encontrado con legajo: {}", legajo);
		return null;
	}

	

	

}
