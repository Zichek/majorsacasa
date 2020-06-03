package es.uji.ei1027.majorsacasa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsacasa.dao.ElderlyDao;
import es.uji.ei1027.majorsacasa.dao.UserDao;
import es.uji.ei1027.majorsacasa.model.Elderly;
import es.uji.ei1027.majorsacasa.model.User;

@Controller
@RequestMapping("/elderly")
public class ElderlyController {

	private ElderlyDao elderlyDao;
	private UserDao userDao;

	@Autowired
	public void setElderlyDao(ElderlyDao elderlyDao) {
		this.elderlyDao = elderlyDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping("/list")
	public String listElderly(Model model, HttpSession session) {
		if (isCasCommittee(session)) {
		model.addAttribute("elderlys", elderlyDao.getElderlys());
		return "elderly/list";
		}
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping("/indexelderly")
	public String listIndexElderly(Model model, HttpSession session) {
		if (isElderly(session)) {
			return "elderly/indexelderly";
		}
		
		model.addAttribute("user", new User());
		return "login";
		
	}

	// Operacions: Crear, llistar, actualitzar, esborrar

	// Crear
	// Creaci� d'objectes
	@RequestMapping(value = "/add")
	public String addElderly(Model model, HttpSession session) {
		if (isElderly(session)) {
			model.addAttribute("elderly", new Elderly());
			return "elderly/add";
		}

		model.addAttribute("user", new User());
		return "login";

	}

	// Gesti� de la resposta del formulari de creaci� d'objectes
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("elderly") Elderly elderly, BindingResult bindingResult) {
		ElderlyValidator elderlyValidator = new ElderlyValidator();
		elderlyValidator.validate(elderly, bindingResult);
		if (bindingResult.hasErrors())
			return "elderly/add";

		elderlyDao.addElderly(elderly);

		userDao.addUser(elderly.getDNI(), elderly.getUserPwd(), "elderly");
		;
		return "redirect:list";
	}

	@RequestMapping(value = "/update/{DNI}", method = RequestMethod.GET)
	public String editElderly(Model model, @PathVariable String DNI, HttpSession session) {
		if (isCasCommittee(session)) {
			model.addAttribute("elderly", elderlyDao.getElderly(DNI));
			return "elderly/update";
		}
		
		model.addAttribute("user", new User());
		return "login";
		
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("elderly") Elderly elderly, BindingResult bindingResult) {
		ElderlyValidator elderlyValidator = new ElderlyValidator();
		elderlyValidator.validate(elderly, bindingResult);
		if (bindingResult.hasErrors())
			return "elderly/update";
		elderlyDao.updateElderly(elderly);
		userDao.updateUser(elderly.getDNI(), elderly.getUserPwd());
		return "redirect:list";
	}

	// Borrar una actividad
	@RequestMapping(value = "/delete/{DNI}")
	public String processDelete(@PathVariable String DNI) {
		elderlyDao.deleteElderly(DNI);
		userDao.deleteUser(DNI);
		return "redirect:../list";
	}

	private boolean isElderly(HttpSession session) {
		User login = (User) session.getAttribute("user");
		return (login != null && login.getRole().equals("elderly"));
	}
	
	private boolean isCasCommittee(HttpSession session) {
		User login = (User) session.getAttribute("user");
		return (login != null && login.getRole().equals("casCommittee"));
	}

}