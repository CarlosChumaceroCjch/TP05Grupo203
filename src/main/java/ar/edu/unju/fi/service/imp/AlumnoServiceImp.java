package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.AlumnoService;
import jakarta.transaction.Transactional;


@Service
public class AlumnoServiceImp implements AlumnoService{
	private static final Logger logger = LoggerFactory.getLogger(AlumnoServiceImp.class);
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
		logger.info("Guardando alumno: {}", alumno);
		alumno.setEstado(true);
		alumnoRepository.save(alumno);
		logger.info("Alumno guardado con éxito");
	}

	@Override
	public List<AlumnoDTO> mostrarAlumnos() {
		logger.info("Mostrando todos los alumnos");
		List<AlumnoDTO> alumnosDTO = alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
		logger.info("Alumnos encontrados: {}", alumnosDTO.size());
		return alumnosDTO;

	}
	@Transactional
	@Override
	public void modificarAlumno(Alumno alumno) {
		 logger.info("Modificando alumno con LU: {}", alumno.getLu());
		 Alumno a = alumnoRepository.findById(alumno.getLu()).orElse(null);
		if (a!=null) {
			a.setLu(alumno.getLu());
			a.setDni(alumno.getDni());
			a.setNombre(alumno.getNombre());
			a.setApellido(alumno.getApellido());
			a.setEmail(alumno.getEmail());
			a.setTelefono(alumno.getTelefono());
			a.setFechaNacimiento(alumno.getFechaNacimiento());
			a.setDomicilio(alumno.getDomicilio());
			a.setEstado(alumno.getEstado());
			a.setMaterias(alumno.getMaterias());
			a.setCarrera(alumno.getCarrera());
			
		}
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (int i=0; i<alumnos.size(); i++) {
			Alumno alum = alumnos.get(i);
			if(alum.getLu().equals(alumno.getLu())) {
				alumnoRepository.save(alumno);
				 logger.info("Alumno modificado con éxito: {}", alumno);
				break;
			}
		}
	}
	
	@Transactional
	@Override
	public void eliminarAlumno(String lu) {
		logger.info("Eliminando alumno con LU: {}", lu);
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (int i = 0; i < alumnos.size(); i++) {
			Alumno alum = alumnos.get(i);
			if (alum.getLu().equals(lu)) {
				alum.setEstado(false);
				alumnoRepository.save(alum);
				logger.info("Alumno eliminado (estado false): {}", alum);
				break;
			}
		}
	}

	@Override
	public Alumno buscarAlumno(String lu) {
		logger.info("Buscando alumno con LU: {}", lu);
		List<Alumno> alumnos = alumnoRepository.findAll();
		for (Alumno a : alumnos) {
			if(a.getLu().equals(lu)) {
				 logger.info("Alumno encontrado: {}", a);
				return a;
			}
		}
		logger.warn("Alumno con LU {} no encontrado", lu);
		return null;
	}

	@Override
	public void inscribirAlumno(Alumno a, MateriaDTO mDto) {
		logger.info("Inscribiendo alumno: {} en materia: {}", a, mDto);
		Materia m= materiaMapDTO.convertirMateriaDTOAMateria(mDto);
		
		  if (m.getCodigo() != null &&
		  materiaRepository.findById(m.getCodigo()).isPresent()) { m =
		  materiaRepository.findById(m.getCodigo()).get(); } else { m =
		  materiaRepository.save(m); } a.getMaterias().add(m); m.getAlumnos().add(a);
		  alumnoRepository.save(a);
		  
		  logger.info("Alumno inscrito con éxito en la materia");
		 
		  
		  	//Revisar ejecucion de ambos en caso de no vincularse en la base y no funcione el filtro en listadoMateria
			/*
			 * a.getMaterias().add(m); m.getAlumnos().add(a); alumnoRepository.save(a);
			 * materiaRepository.save(m);
			 * logger.info("Alumno inscrito con éxito en la materia");
			 */
			 
	}

	@Override
	public List<Alumno> filtrar(String cod) {
		logger.info("Filtrando alumnos por código de carrera: {}", cod);
		  List<Alumno> alumnos = alumnoRepository.findAll(); List<Alumno>
			alumnosFiltrados = new ArrayList<>();
			for (Alumno a : alumnos) {
					if (a.getCarrera().getCod().equals(cod) ) {
						alumnosFiltrados.add(a);
				}
			}
			 logger.info("Alumnos filtrados encontrados: {}", alumnosFiltrados.size());
			return alumnosFiltrados;
		 
	}

	
}
