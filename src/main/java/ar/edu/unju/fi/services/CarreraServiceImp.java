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
		c.setStatus(true);
		carreraRepository.save(c);
	}

	@Override
	public List<Carrera> MostrarCarreras(Carrera c) {
		// TODO Auto-generated method stub
		return carreraRepository.findAll();
	}

}
