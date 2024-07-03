package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;

@Controller
public class MateriaController {
	@Autowired
	MateriaDTO nuevaMateriaDTO;
	@Autowired
	MateriaService materiaService;
	@Autowired
	DocenteService docenteService;
	@Autowired
	CarreraService carreraService;
	
	@GetMapping("/formularioMateria")
	public ModelAndView mostrarFormMateria() {
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria",nuevaMateriaDTO);
		modelView.addObject("listadoDocentes",docenteService.mostrarDocentesDTO());
		modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		modelView.addObject("flag",false);
		return modelView;
	};
	
	@GetMapping("/listaDeMaterias")
	public ModelAndView Lista() {
		ModelAndView modelView= new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.listar());
		modelView.addObject("listadoDocentes",docenteService.mostrarDocentesDTO());
		modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		return modelView;
	}
	@PostMapping("/guardarMateria")
	public ModelAndView guardarMateria(@Valid @ModelAttribute("nuevaMateria") Materia materia,BindingResult resultado) {	
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		try {
			if(resultado.hasErrors()) {
				modelView.setViewName("formMateria");
				modelView.addObject("listadoDocentes",docenteService.mostrarDocentesDTO());
				System.out.println("Fallo materia");
			}
			else {
				//materia.setCarrera(carreraService.buscarCarrera(materia.getCarrera().getCod()));
				materia.setDocente(docenteService.buscarDocente(materia.getDocente().getLegajo()));
				materiaService.guardar(materia);
				System.out.println("Materia guardada correctamente");
			}
			
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargamateriaErrorMessage", "Error al cargar en BD" + e.getMessage());
			System.out.println(e.getMessage());
		}
		modelView.addObject("listadoMaterias", materiaService.listar());
		return modelView;

	}
	
	@GetMapping("/eliminarMateria/{codigo}")
	public ModelAndView eliminarMateria(@PathVariable(name="codigo") Long codigo) {
		materiaService.eliminar(codigo);
		ModelAndView modelView = new ModelAndView("listaDematerias");
		modelView.addObject("listadoMaterias",materiaService.listar());
		return modelView;
	}
	
	@GetMapping("/modificarMateria/{codigo}")
	public ModelAndView modificarMateria(@PathVariable(name="codigo") Long codigo) {
		MateriaDTO materia = materiaService.obtenerPorId(codigo);
		ModelAndView modelView = new ModelAndView("formMateria");
		modelView.addObject("nuevaMateria",materia);
		modelView.addObject("listadoDocentes",docenteService.mostrarDocentesDTO());
		modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		modelView.addObject("flag",true);
		return modelView;
	}
	
	@PostMapping("/modificarMateria")
	public ModelAndView modificarMateria(@Valid @ModelAttribute("nuevaMateria") Materia m, BindingResult resultado) {
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		try {
			if(resultado.hasErrors()) {
				modelView.setViewName("formMateria");
				modelView.addObject("listadoDocentes",docenteService.mostrarDocentesDTO());
				System.out.println("Fallo mod materia");
			}
			else {
				m.setCarrera(carreraService.buscarCarrera(m.getCarrera().getCod()));
				m.setDocente(docenteService.buscarDocente(m.getDocente().getLegajo()));
				materiaService.modificarMateria(m);
				modelView.addObject("listadoMaterias",materiaService.listar());
			}
			
		} catch (Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargamateriaErrorMessage", "Error al cargar en BD" + e.getMessage());
			System.out.println(e.getMessage());
		}
		modelView.addObject("listadoMaterias", materiaService.listar());
		return modelView;
		
	}
}
