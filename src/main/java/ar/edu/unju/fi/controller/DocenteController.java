package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.dto.DocenteDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.DocenteService;

@Controller
public class DocenteController {
	@Autowired
	DocenteDTO nuevoDocenteDTO;
	
	@Autowired
	DocenteService docenteService;
	
	@GetMapping("/formDocente")
	 public ModelAndView getIndex() {
		//vista
		ModelAndView formD = new ModelAndView("formDocente");
		//agrega el object
		formD.addObject("nuevoDocente",nuevoDocenteDTO);
		formD.addObject("band",false);
		
        return formD;
    }
	
	
	@PostMapping("/guardarDocente")
	public ModelAndView saveDocente(@ModelAttribute("nuevoDocente") DocenteDTO guardarDocente) {
		//guardar
		docenteService.guardarDocente(guardarDocente);
		
		//mostrar listado
	    ModelAndView formD = new ModelAndView("listaDeDocentes");
		formD.addObject("listadoDocentes",docenteService.mostrarDocentes());
		return formD;
	}
	
	@GetMapping("/borrarDocente/{legajo}")
	public ModelAndView deleteDocente(@PathVariable(name="legajo") String legajo) {
		//borrar
		docenteService.borrarDocente(legajo);
		
		//mostrar nueva lista
		ModelAndView formD = new ModelAndView("listaDeDocentes");
		formD.addObject("listadoDocentes", docenteService.mostrarDocentes());	
		
		return formD;		
		}
	
	@GetMapping("/modificarDocente/{legajo}")
	public ModelAndView editCarrera(@PathVariable(name="legajo") String legajo) {
		//buscar
		Docente modificarDocente = docenteService.buscarDocente(legajo);
		
		//mostrar el nuevo formulario
		ModelAndView formD = new ModelAndView("formDocente");
		formD.addObject("nuevoDocente", modificarDocente);	
		formD.addObject("band", true);
		return formD;		
		}
	
	@PostMapping("/modificarDocente")
	public ModelAndView updateCarrera(@ModelAttribute("nuevoDocente") Docente docenteModificado) {
					
		//guardar
		docenteService.modificarDocente(docenteModificado);
		
		//mostrar el listado
		ModelAndView formD = new ModelAndView("listaDeDocentes");
		formD.addObject("listadoDocentes", docenteService.mostrarDocentes());	
		
		return formD;		
	}
	
	@GetMapping("/listaDeDocentes")
	public ModelAndView Lista() {
		ModelAndView formD= new ModelAndView("listaDeDocentes");
		formD.addObject("listadoDocentes",docenteService.mostrarDocentes());
		return formD;
	}



}
