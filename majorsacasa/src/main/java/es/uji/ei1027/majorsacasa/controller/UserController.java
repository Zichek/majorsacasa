package es.uji.ei1027.majorsacasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors; 
import org.springframework.validation.Validator;

import es.uji.ei1027.majorsacasa.dao.UserDao;
import es.uji.ei1027.majorsacasa.model.User; 


class UserValidator implements Validator { 
	@Override
	public boolean supports(Class<?> cls) { 
		return User.class.isAssignableFrom(cls);
	}
	@Override 
	public void validate(Object obj, Errors errors) {
	  // Exercici: Afegeix codi per comprovar que 
		User user = (User) obj;
		if (user.getUsername().equals("")) {
			errors.rejectValue("usuario", "obligatori", "DNI or CIF invalid");
		}
		if (user.getPassword().equals("") || user.getPassword().length() < 8) {
			errors.rejectValue("pwd", "obligatori", "No password");
		}
	}
}

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String checkLogin(@ModelAttribute("user") User user,  		
				BindingResult bindingResult, HttpSession session) {
//		UserValidator userValidator = new UserValidator(); 
//		userValidator.validate(user, bindingResult); 
		if (bindingResult.hasErrors()) {
			return "login";
		}
	       // Comprova que el login siga correcte 
		// intentant carregar les dades de l'usuari 
		user = userDao.getUser(user.getUsername(), user.getPassword()); 
		if (user == null) {
			bindingResult.rejectValue("password", "badpw", "Wrong password"); 
			return "login";
		}
		// Autenticats correctament. 
		// Guardem les dades de l'usuari autenticat a la sessió
		session.setAttribute("user", user); 
		if (user.getRole().equals("elderly"))
			return "redirect:/elderly/list";
		else if (user.getRole().equals("company"))
			return "redirect:/company/indexCompany";
		else if (user.getRole().equals("casManager")) 
			return "redirect:/cas/manager";
		else if (user.getRole().equals("casComite"))
			return "redirect:manager/indexCasCommitee";
		
		// Torna a la pàgina principal
		return "redirect:/";
	}

	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate(); 
		return "redirect:/";
	}
}
