package es.uji.ei1027.majorsacasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei1027.majorsacasa.model.User;


@Controller
public class ProfileController {
	@RequestMapping("/myprofile")
	public String listIndexElderly(Model model, HttpSession session) {
		User login = (User) session.getAttribute("user");
		if(login==null) {
			model.addAttribute("user",new User());
			return "login";			
		}
		
		if (login.getRole().equals("elderly"))
			return "redirect:/elderly/indexelderly"; //FUNCIONA
		else if (login.getRole().equals("company")) //NO IMPLEMENTADO
			return "redirect:/company/indexcompany";
		else if (login.getRole().equals("casManager"))  //FUNCIONA
			return "redirect:/cas/manager";
		else if (login.getRole().equals("casCommittee")) //FUNCIONA
			return "redirect:/cas/committee";
		
		// Torna a la pàgina principal
		return "redirect:/";
			
	}

}
