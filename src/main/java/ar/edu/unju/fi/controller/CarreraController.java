package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.CarreraDTO;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;

@Controller
public class CarreraController {

	
	@Autowired
	CarreraDTO nuevaCarreraDTO;
	
	@Autowired
	CarreraService carreraService;
	
	@Autowired
	MateriaService materiaService;

	@GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera() {
		//Vista formCarrera.html
		ModelAndView modelView= new ModelAndView("formCarrera");
		//Agrega el Objeto
		modelView.addObject("nuevaCarrera",nuevaCarreraDTO);
		modelView.addObject("materias",materiaService.listar());
		modelView.addObject("flag", false);
		return modelView;
		
	}
	
	@GetMapping("/listaDeCarreras")
	public ModelAndView Lista() {
		ModelAndView modelView= new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		return modelView;
	}
	
	
	@PostMapping("/guardarCarrera")
	public ModelAndView saveCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera c,BindingResult resultado) {
		ModelAndView modelView= new ModelAndView();
		try {
			if(resultado.hasErrors()) {
				modelView.setViewName("formCarrera");
				modelView.addObject("materias",materiaService.listar());
			}
			else
			{
				carreraService.guardarCarrera(c);
				System.out.println("Carrera guardada correctamente");
			}
		}catch(Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("cargaCarreraErrorMessage", "Error al cargar en la BD" + e.getMessage());
			System.out.println(e.getMessage());
		}
		if (!resultado.hasErrors()) {
			modelView.setViewName("listaDeCarreras");
			modelView.addObject("materias",materiaService.listar());
			modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		}
		
		return modelView;
		
	}
	
	@GetMapping("/eliminarCarrera/{cod}")
	public ModelAndView eliminarCarreraDeLista(@PathVariable(name="cod") String cod) {

		carreraService.eliminarCarrera(cod);
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		
		return modelView;
	}
	
	@GetMapping("/modificarCarrera/{cod}")
	public ModelAndView modificarCarrera(@PathVariable(name="cod")String cod) {
		Carrera carrera = carreraService.buscarCarrera(cod);
		ModelAndView modelView =new ModelAndView("formCarrera");
		modelView.addObject("nuevaCarrera",carrera);
		modelView.addObject("materias", materiaService.listar());
		modelView.addObject("flag", true);	
		return modelView;
	}
	
	@PostMapping("/modificarCarrera")
	public ModelAndView modifcarCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera cMod,BindingResult resultado)
	{	
		ModelAndView modelView = new ModelAndView();
		try {
			if (resultado.hasErrors()) {
				modelView.setViewName("formCarrera");
				modelView.addObject("materias",materiaService.listar());
			}
			else {
				carreraService.modifcarCarrera(cMod);
				System.out.println("Carrera Modificada Correctemante");
			}
			
		}catch(Exception e) {
			modelView.addObject("errors", true);
			modelView.addObject("ModificacionCarreraErrorMessage", "Error al cargar en la BD" + e.getMessage());
			System.out.println(e.getMessage());
		}
		if(!resultado.hasErrors()) {
			modelView.setViewName("listDeCarreras");
			modelView.addObject("listadoCarreras",carreraService.MostrarCarreras());
		}
		return modelView;
		
	}
	
	 
	
}
