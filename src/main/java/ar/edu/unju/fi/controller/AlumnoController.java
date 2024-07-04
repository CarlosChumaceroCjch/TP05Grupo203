package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;

@Controller
public class AlumnoController {
	@Autowired
	Alumno nuevoAlumno;
	@Autowired
	AlumnoDTO nuevoAlumnoDTO;
	@Autowired
	Materia nuevaMateria;
	@Autowired
	MateriaService materiaService;
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	CarreraService carreraService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView mostrarFormulario() {
		ModelAndView modelView = new ModelAndView("formAlumno");
		
		modelView.addObject("nuevoAlumno", nuevoAlumnoDTO);
		modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		modelView.addObject("flag", false);
		return modelView;
	};
	
	@GetMapping("/listarAlumno")
	public ModelAndView listarAlumnos() {
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		
		return modelView;
	}
	
	@PostMapping("/guardarAlumno")
	public ModelAndView guardarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumno,BindingResult resultado) {
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		try {
			if(resultado.hasErrors()) {
				modelView.setViewName("formAlumno");
				modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
				System.out.println("Fallo alumno");
			}
			else {
				alumno.setCarrera(carreraService.buscarCarrera(alumno.getCarrera().getCod()));
				alumnoService.guardarAlumno(alumno);
				System.out.println("alumno guardada correctamente");
			}
			
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaAlumnoErrorMessage", "Error al cargar en BD" + e.getMessage());
			System.out.println(e.getMessage());
		}
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		return modelView;
	};
	
	
	@GetMapping("/modificarAlumno/{lu}")
	public ModelAndView modificarAlumno(@PathVariable(name="lu") String lu) {
		
		Alumno alumnoAModificar = alumnoService.buscarAlumno(lu);
		ModelAndView modelView = new ModelAndView("formAlumno");
		modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		modelView.addObject("nuevoAlumno", alumnoAModificar);
		modelView.addObject("flag", true);
		return modelView;
	};
	
	@PostMapping("modificarAlumno")
	public ModelAndView modificarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoModificar, BindingResult resultado) {

		ModelAndView modelView = new ModelAndView("listaDeAlumnos");

		try {
			if(resultado.hasErrors()) {
				modelView.setViewName("formAlumno");
				modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
				System.out.println("Fallo alumno");
			}
			else {
				alumnoModificar.setCarrera(carreraService.buscarCarrera(alumnoModificar.getCarrera().getCod()));
				alumnoService.modificarAlumno(alumnoModificar);
				System.out.println("alumno guardada correctamente");
			}
			
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaAlumnoErrorMessage", "Error al cargar en BD" + e.getMessage());
			System.out.println(e.getMessage());
		}
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		return modelView;
	}
	
	@GetMapping("/eliminarAlumno/{lu}")
	public ModelAndView eliminarAlumno(@PathVariable(name="lu") String lu) {
		
		alumnoService.eliminarAlumno(lu);
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		
		return modelView;
	};
	
	//Tp5 parte 2/Inscipriciones
	@GetMapping("/formularioInscripcion")
	public ModelAndView InscribirAlumno(){
		ModelAndView modelView = new ModelAndView("InscripcionAMaterias");
		modelView.addObject("nuevoAlumno",nuevoAlumno);
		modelView.addObject("nuevaMateria",nuevaMateria);
		modelView.addObject("listadoMaterias",materiaService.listar());
		return modelView;
	}
	@PostMapping("/guardarInscripcion")
	public ModelAndView formInscripcion(@ModelAttribute("nuevoAlumno")Alumno a,@ModelAttribute("nuevaMateria")Materia m) {
		ModelAndView modelView=new ModelAndView("ListaDeAlumnos");

		try {
			if (alumnoService.buscarAlumno(a.getLu())!=null){
				alumnoService.inscribirAlumno(alumnoService.buscarAlumno(a.getLu()), materiaService.obtenerPorId(m.getCodigo()));
				modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumnos());
			}
		}
		catch( Exception e){
			boolean errors = true;
			modelView.addObject("errors", errors);
			modelView.addObject("cargaAlumnoErrorMessage", " Error al cargar en la BD " + e.getMessage());
			System.out.println(e.getMessage());
		}
		return modelView;	
	}
	//Tp5parte2 filtrado alumnos
	
	@GetMapping("/filtrarAlumnosCar/{cod}")
	public ModelAndView filtrarLosAlumnos(@PathVariable(name="cod")String cod) {
		ModelAndView modelView = new ModelAndView("ListaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.filtrar(cod));
		return modelView;
	}

}
