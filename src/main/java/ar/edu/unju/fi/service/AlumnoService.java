package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Service
public interface AlumnoService {
	public void guardarAlumno(AlumnoDTO alumno);
	public List<Alumno> mostrarAlumnos();
	public void eliminarAlumno(String lu);
	public void modificarAlumno(AlumnoDTO alumno);
	public Alumno buscarAlumno(String lu);
}
