package ar.edu.unju.fi.service.imp;

import java.util.List;
import java.util.stream.Collectors;

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
	public void guardarCarrera(Carrera c) {
		// TODO Auto-generated method stub
		//c.setStatus(true);
		for(Materia m:c.getMaterias()) {
			m.setCarrera(c);
		}
		System.out.println("Guardando carrera: " + c);
		carreraRepository.save(c);
	}

	@Override
	public List<Carrera> MostrarCarreras() {
		System.out.println("Mostrando carreras: " + carreraRepository.findAll());
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
				System.out.println("Eliminando carrera: " + carrera);
				carreraRepository.save(carrera);
				break;
			}
		}
	}	

	@Override
	public void modifcarCarrera(Carrera c) {
			List<Carrera> listadoCarreras = carreraRepository.findAll();
			for (int i = 0; i < listadoCarreras.size(); i++) {
				Carrera carrera = listadoCarreras.get(i);
				if (carrera.getCod().equals(c.getCod())) {
					for(Materia m:c.getMaterias()) {
						m.setCarrera(c);
					}
					System.out.println("Modificando carrera: " + c);
					carreraRepository.save(c);
					break;
				}
			}
			 
		
	}

	@Override
	public Carrera buscarCarrera(String codigo) {
		List<Carrera>listadoCarreras = carreraRepository.findAll();
		
		for (Carrera c :listadoCarreras) {
			if (c.getCod().equals(codigo)) {
				System.out.println("Carrera encontrada: " + c);
				return c;
			}
		}
		System.out.println("Carrera no encontrada con código: " + codigo);
		return null;
	}
	
	

}
