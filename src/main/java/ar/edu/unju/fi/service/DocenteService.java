package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.model.Docente;

public interface DocenteService {
	public void guardarDocente(Docente docente);
	public List<DocenteDTO> mostrarDocentesDTO();
	public void borrarDocente(String legajo);
	public void modificarDocente(Docente docente);
	public Docente buscarDocente(String legajo);

}
