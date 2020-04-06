package es.uji.ei1027.majorsacasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import es.uji.ei1027.majorsacasa.dao.ElderlyDao;
import es.uji.ei1027.majorsacasa.model.Elderly;

@Controller
@RequestMapping("/elderly")
public class ElderlyController {

	@RequestMapping("/test")
	public String provaWeb(Model model) {
		String message = "Provant la Web de Toopots";
		model.addAttribute("message", message);
		return "prova";
	}
	@Autowired
	ElderlyDao elderlyDao;

//	@RequestMapping("/provaActividad")
//	public String provaUnaActividad(Model model) {
//		Elderly elderly = elderlyDao.getElderly("Juan");
//		model.addAttribute("message", elderly.toString());
//		return "elderly";
//	}

	@RequestMapping("/list")
	public String listElderly(Model model) {
		model.addAttribute("elderlys", elderlyDao.getElderlys());
		return "elderly/list";
	}
	
	
	// Operacions: Crear, llistar, actualitzar, esborrar
	   
	   //Crear
	   // Creaci� d'objectes
	   @RequestMapping(value="/add") 
	   public String addElderly(Model model) {
	       model.addAttribute("elderly", new Elderly());
	       return "elderly/add";
	   }
	   // Gesti� de la resposta del formulari de creaci� d'objectes
	   @RequestMapping(value="/add", method=RequestMethod.POST) 
	   public String processAddSubmit(@ModelAttribute("elderly") Elderly elderly,
	                                   BindingResult bindingResult) {  
	        if (bindingResult.hasErrors()) 
	               return "elderly/add";
	        elderlyDao.addElderly(elderly);
	        return "redirect:list"; 
	    }
	   
	   //Modificar
	   //Modificaci� d'objectes
	   @RequestMapping(value="/update/{DNI}", method = RequestMethod.GET) 
	   public String editElderly(Model model, @PathVariable String DNI) { 
	       model.addAttribute("elderly", elderlyDao.getElderly(DNI));
	       return "elderly/update"; 
	   }
	   //Resposta de modificaci� d'objectes
	   @RequestMapping(value="/update/{DNI}", method = RequestMethod.POST) 
	   public String processUpdateSubmit(@PathVariable String DNI, 
	                           @ModelAttribute("elderly") Elderly elderly, 
	                           BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) 
	            return "elderly/update";
	        elderlyDao.getElderly(DNI);
	        return "redirect:../list"; 
	   }
	   
	   //Borrar una actividad
	   @RequestMapping(value="/delete/{DNI}")
	   public String processDelete(@PathVariable String DNI) {
	          elderlyDao.deleteElderly(DNI);
	          return "redirect:../list"; 
	   }

}