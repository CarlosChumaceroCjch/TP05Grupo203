package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService{

	@Autowired
	AlumnoRepository alumnoRepository;
	@Autowired
	MateriaRepository materiaRepository;
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	MateriaMapDTO materiaMapDTO;
	
	@Override
	public void guardarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		alumno.setEstado(true);
		alumnoRepository.save(alumno);
	}

	@Override
	public List<AlumnoDTO> mostrarAlumnos() {
		// TODO Auto-generated method stub
		return alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
	}

	@Override
	public void modificarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (int i=0; i<alumnos.size(); i++) {
			Alumno alum = alumnos.get(i);
			if(alum.getLu().equals(alumno.getLu())) {
				alumnoRepository.save(alumno);
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

	@Override
	public void inscribirAlumno(Alumno a, MateriaDTO mDto) {
		Materia m= materiaMapDTO.convertirMateriaDTOAMateria(mDto);
		a.getMaterias().add(m);
		m.getAlumnos().add(a);
		alumnoRepository.save(a);
		materiaRepository.save(m);
	}

	@Override
	public List<Alumno> filtrar(String cod) {
		  List<Alumno> alumnos = alumnoRepository.findAll(); List<Alumno>
			alumnosFiltrados = new ArrayList<>();
			for (Alumno a : alumnos) {
					if (a.getCarrera().getCod().equals(cod) ) {
						alumnosFiltrados.add(a);
				}
			}
			return alumnosFiltrados;
		 
	}

	
}
