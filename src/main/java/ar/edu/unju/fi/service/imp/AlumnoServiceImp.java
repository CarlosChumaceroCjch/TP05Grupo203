package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService{

	@Autowired
	AlumnoRepository alumnoRepository;
	
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Override
	public void guardarAlumno(AlumnoDTO alumno) {
		// TODO Auto-generated method stub
		alumnoRepository.save(alumnoMapDTO.convertirDTOaAlumno(alumno));
	}

	@Override
	public List<Alumno> mostrarAlumnos() {
		// TODO Auto-generated method stub
		return alumnoRepository.buscarAlumnoPorEstado(true);
	}

	@Override
	public void modificarAlumno(AlumnoDTO alumno) {
		// TODO Auto-generated method stub
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (int i=0; i<alumnos.size(); i++) {
			Alumno alum = alumnos.get(i);
			if(alum.getLu().equals(alumno.getLu())) {
				alumnoRepository.save(alumnoMapDTO.convertirDTOaAlumno(alumno));
				break;
			}
		}
	}

	@Override
	public void eliminarAlumno(String lu) {
		// TODO Auto-generated method stub
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (int i = 0; i < alumnos.size(); i++) {
			Alumno alum = alumnos.get(i);
			if (alum.getLu().equals(lu)) {
				alum.setEstado(false);
				alumnoRepository.save(alum);
				break;
			}
		}
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
