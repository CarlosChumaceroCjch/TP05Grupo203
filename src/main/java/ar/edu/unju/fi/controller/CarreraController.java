package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.services.CarreraService;




@Controller
public class CarreraController {
	
	@Autowired
	Carrera nuevaCarrera = new Carrera();
	
	@Autowired
	CarreraService carreraService;

	@GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera() {
		//Vista formCarrera.html
		ModelAndView modelView= new ModelAndView("formCarrera");
		//Agrega el Objeto
		modelView.addObject("nuevaCarrera",nuevaCarrera);
		modelView.addObject("flag", false);
		return modelView;
		
	}
	/**
	@GetMapping("/listaDeCarreras")
	public ModelAndView Lista() {
		ModelAndView modelView= new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",ListadoCarreras.listarCarreras());
		return modelView;
	}
	
	**/
	@PostMapping("/guardarCarrera")
	public ModelAndView saveCarrera(@ModelAttribute("nuevaCarrera") Carrera c) {

		//Guardado de carrera
		//ListadoCarreras.agCarrera(c);
		carreraService.guardarCarrera(c);
		
		//mostrar la vista
		ModelAndView modelView= new ModelAndView("listaDeCarreras");
		//modelView.addObject("listadoCarreras",ListadoCarreras.listarCarreras());
		modelView.addObject("listadoCarreras",carreraService.MostrarCarreras(c));
		
		return modelView;
		
	}
	/**
	@GetMapping("/eliminarCarrera/{codigo}")
	public ModelAndView eliminarCarreraDeLista(@PathVariable(name="codigo") String codigo) {
		//Borrar
		ListadoCarreras.eliminarCarrera(codigo);
		
		//Mostrar Nuevo Listadp
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",ListadoCarreras.listarCarreras());
		
		return modelView;
	}
	
	@GetMapping("/modificarCarrera/{codigo}")
	public ModelAndView modificarCarrera(@PathVariable(name="codigo")String codigo) {
		Carrera carrera = ListadoCarreras.buscaCarreraCod(codigo);
		ModelAndView modelView =new ModelAndView("formCarrera");
		modelView.addObject("nuevaCarrera",carrera);
		modelView.addObject("flag", true);		
		return modelView;
	}
	
	@PostMapping("/modificarCarrera")
	public ModelAndView modifcarCarrera(@ModelAttribute("nuevaCarrera") Carrera cMod)
	{	ListadoCarreras.modificarCarrera(cMod);
		ModelAndView modelView = new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",ListadoCarreras.listarCarreras());
		return modelView;
		
	}
	
	 */
	
}
