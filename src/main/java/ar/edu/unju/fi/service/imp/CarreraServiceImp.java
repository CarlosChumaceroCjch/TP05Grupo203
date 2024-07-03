package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService{

	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	@Override
	public void guardarCarrera(CarreraDTO cDTO) {
		// TODO Auto-generated method stub
		//c.setStatus(true);
		Carrera c=carreraMapDTO.convertirDTOaCarrera(cDTO);
		for(Materia m:c.getMaterias()) {
			m.setCarrera(c);
		}
		carreraRepository.save(c);
	}

	@Override
	public List<Carrera> MostrarCarreras() {
		// TODO Auto-generated method stub
		return carreraRepository.findCarreraByStatus(true);
	}

	@Override
	public void eliminarCarrera(String codigo) {
		// TODO Auto-generated method stub
		List<Carrera>listadoCarreras = carreraRepository.findAll();
		for (int i = 0; i < listadoCarreras.size(); i++) {
			Carrera carrera = listadoCarreras.get(i);
			if (carrera.getCod().equals(codigo)) {
				carrera.setStatus(false);
				carreraRepository.save(carrera);
				break;
			}
		}
	}	

	@Override
	public void modifcarCarrera(CarreraDTO cDTO) {
		Carrera c=carreraMapDTO.convertirDTOaCarrera(cDTO);
		List<Carrera>listadoCarreras = carreraRepository.findAll();
		for (int i = 0; i < listadoCarreras.size(); i++) {
			Carrera carrera = listadoCarreras.get(i);
			if (carrera.getCod().equals(c.getCod())) {
				carreraRepository.save(c);
				System.out.println("Entro bieny = "+ c.getMaterias().isEmpty());
				break;
			}
		}
		
	}

	@Override
	public Carrera buscarCarrera(String codigo) {
		List<Carrera>listadoCarreras = carreraRepository.findAll();
		for (Carrera c : listadoCarreras) {
			if (c.getCod().equals(codigo)) {
				return c;
			}
		}
		return null;
	}

}
