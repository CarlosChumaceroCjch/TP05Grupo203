package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.DocenteService;
import ar.edu.unju.fi.service.MateriaService;

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
		modelView.addObject("listadoDocentes",docenteService.mostrarDocentes());
		modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		modelView.addObject("flag",false);
		return modelView;
	};
	
	@GetMapping("/listaDeMaterias")
	public ModelAndView Lista() {
		ModelAndView modelView= new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.listar());
		return modelView;
	}
	@PostMapping("/guardarMateria")
	public ModelAndView guardarMateria(@ModelAttribute("nuevaMateria") MateriaDTO materia) {
		/*
		 * System.out.println("el codigo docente es "+materia.getDocente().getLegajo());
		 * System.out.println("el codigo carrera es "+materia.getCarrera().getCod());
		 */
		materia.setCarrera(carreraService.buscarCarrera(materia.getCarrera().getCod()));
		materia.setDocente(docenteService.buscarDocente(materia.getDocente().getLegajo()));
		materiaService.guardar(materia);
		ModelAndView modelView = new ModelAndView("listaDeMaterias");
		
		/*
		 * try { materiaService.guardar(materia); }catch(Exception e){
		 * modelView.addObject("errors",true);
		 * modelView.addObject("cargamateriaErrorMessage","Error al cargar en BD"+e.
		 * getMessage()); }
		 **/modelView.addObject("listadoMaterias",materiaService.listar()); 
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
		ModelAndView modelView = new ModelAndView("formCarrera");
		modelView.addObject("nuevaMateria",materia);
		modelView.addObject("flag",true);
		return modelView;
	}
	
	@PostMapping("/modificarMateria")
	public ModelAndView modificarMateria(@ModelAttribute("nuevaMateria") MateriaDTO m) {
		materiaService.modificarMateria(m);
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",materiaService.listar());
		return modelView;
		
	}
}
