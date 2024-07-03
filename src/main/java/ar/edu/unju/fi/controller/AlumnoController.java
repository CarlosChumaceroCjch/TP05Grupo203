package ar.edu.unju.fi.controller;

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
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import jakarta.validation.Valid;

@Controller
public class AlumnoController {
	
	@Autowired
	AlumnoDTO nuevoAlumnoDTO;
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
}
