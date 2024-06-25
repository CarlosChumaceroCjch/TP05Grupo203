package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.service.AlumnoService;

@Controller
public class AlumnoController {
	
	@Autowired
	AlumnoDTO nuevoAlumnoDTO;
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView mostrarFormulario() {
		ModelAndView modelView = new ModelAndView("formAlumno");
		
		modelView.addObject("nuevoAlumno", nuevoAlumnoDTO);
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
	public ModelAndView guardarAlumno(@ModelAttribute("nuevoAlumno") AlumnoDTO alumno) {
		
		alumnoService.guardarAlumno(alumno);
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		return modelView;
	};
	
	
	@GetMapping("/modificarAlumno/{lu}")
	public ModelAndView modificarAlumno(@PathVariable(name="lu") String lu) {
		
		Alumno alumnoAModificar = alumnoService.buscarAlumno(lu);
		ModelAndView modelView = new ModelAndView("formAlumno");
		
		modelView.addObject("nuevoAlumno", alumnoAModificar);
		modelView.addObject("flag", true);
		return modelView;
	};
	
	@PostMapping("modificarAlumno")
	public ModelAndView modificarAlumno(@ModelAttribute("nuevoAlumno") AlumnoDTO alumnoModificar) {
		alumnoService.modificarAlumno(alumnoModificar);
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumnos());
		modelView.addObject("flag", true);
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
