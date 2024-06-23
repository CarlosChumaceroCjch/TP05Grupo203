package ar.edu.unju.fi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;

@Service
public class CarreraServiceImp implements CarreraService{

	
	@Autowired
	CarreraRepository carreraRepository;
	@Override
	public void guardarCarrera(Carrera c) {
		// TODO Auto-generated method stub
		//c.setStatus(true);
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
			if (carrera.getCodigo().equals(codigo)) {
				carrera.setStatus(false);
				carreraRepository.save(carrera);
				break;
			}
		}
	}	

	@Override
	public void modifcarCarrera(Carrera c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Carrera buscarCarrera(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

}
