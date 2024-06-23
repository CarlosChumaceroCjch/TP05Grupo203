package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService{

	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Override
	public void guardarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		alumnoRepository.save(alumno);
	}

	@Override
	public List<Alumno> mostrarAlumnos() {
		// TODO Auto-generated method stub
		return alumnoRepository.findAll();
	}

	@Override
	public void modificarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (int i=0; i<alumnos.size(); i++) {
			Alumno alum = alumnos.get(i);
			if(alum.getLu().equals(alumno.getLu())) {
				alumnos.set(i, alumno);
				break;
			}
		}
	}

	@Override
	public void eliminarAlumno(String lu) {
		// TODO Auto-generated method stub
		List<Alumno> alumnos = alumnoRepository.findAll();
		alumnos.removeIf(alumno -> alumno.getLu().equals(lu));
	}

	@Override
	public Alumno buscarAlumno(String lu) {
		// TODO Auto-generated method stub
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (Alumno a : alumnos) {
			if(a.getLu().equals(lu)) {
				return a;
			}
		}
		return null;
	}

	
}
