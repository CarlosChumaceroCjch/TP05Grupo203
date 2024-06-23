package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService{
	@Autowired
	DocenteRepository docenteRepository;
	
	@Autowired
	DocenteMapDTO carreraMapDTO;

	@Override
	public void guardarDocente(DocenteDTO docenteDTO) {
		// TODO Auto-generated method stub
		docenteDTO.setEstado(true);
		docenteRepository.save(carreraMapDTO.convertirDocenteDTOADocente(docenteDTO));
		
	}

	@Override
	public List<Docente> mostrarDocentes() {
		// TODO Auto-generated method stub
		return docenteRepository.findDocenteByEstado(true);
	}

	@Override
	public void borrarDocente(String legajo) {
		// TODO Auto-generated method stub
		List<Docente> docentes = docenteRepository.findAll();
		for (int i = 0; i < docentes.size(); i++) {
		      Docente docente = docentes.get(i);
		      if (docente.getLegajo().equals(legajo)) {
		        docente.setEstado(false);
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
				docentes.set(i, docente);
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
				return d;
			}		
		}
		return null;
	}

	

	

}
