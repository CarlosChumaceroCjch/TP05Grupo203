package ar.edu.unju.fi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Carrera;

@Service
public interface CarreraService {
	
	public void guardarCarrera(Carrera c);
	public List<Carrera> MostrarCarreras();
	public void eliminarCarrera(String codigo);
	public void modifcarCarrera(Carrera c);
	public Carrera buscarCarrera(String codigo);
}
