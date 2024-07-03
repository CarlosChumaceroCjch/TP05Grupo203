package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.service.DocenteService;
import jakarta.validation.Valid;

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
	public ModelAndView saveDocente(@Valid @ModelAttribute("nuevoDocente") Docente guardarDocente,BindingResult resultado) {
		ModelAndView formD = new ModelAndView("listaDeDocentes");
		try {
			if(resultado.hasErrors()) {
				formD.setViewName("formDocente");
				System.out.println("fallo");
			}else {
				docenteService.guardarDocente(guardarDocente);
				System.out.println("Docente guardado correctamente");	
			}


		}catch(Exception e)
		{
			boolean errors = true;
			formD.addObject("errors", errors);
			formD.addObject("cargaDocenteErrorMessage", " Error al Cargar en la BD " + e.getMessage());
			System.out.println(e.getMessage());
		}

		formD.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
		return formD;
	}
	
	@GetMapping("/borrarDocente/{legajo}")
	public ModelAndView deleteDocente(@PathVariable(name="legajo") String legajo) {
		//borrar
		docenteService.borrarDocente(legajo);
		
		//mostrar nueva lista
		ModelAndView formD = new ModelAndView("listaDeDocentes");
		formD.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());	
		
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
	public ModelAndView updateDocente(@Valid @ModelAttribute("nuevoDocente") Docente docenteModificado,BindingResult resultado) {
		ModelAndView formD = new ModelAndView();
		try {
			if (resultado.hasErrors()) {
				formD.setViewName("formDocente");
			} else {
				docenteService.modificarDocente(docenteModificado);
				formD.addObject("listadoDocentes", docenteService.mostrarDocentesDTO());
			}
		}catch(Exception e)
		{
			boolean errors = true;
			formD.addObject("errors", errors);
			formD.addObject("cargaDocenteErrorMessage", " Error al Cargar en la BD " + e.getMessage());
			System.out.println(e.getMessage());
		}	
		
		return formD;		
	}
	
	@GetMapping("/listaDeDocentes")
	public ModelAndView Lista() {
		ModelAndView formD= new ModelAndView("listaDeDocentes");
		formD.addObject("listadoDocentes",docenteService.mostrarDocentesDTO());
		return formD;
	}



}
