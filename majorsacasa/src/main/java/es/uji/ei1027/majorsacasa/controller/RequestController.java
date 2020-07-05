package es.uji.ei1027.majorsacasa.controller;

import javax.servlet.http.HttpSession;

import es.uji.ei1027.majorsacasa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.dao.ElderlyDao;
import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.model.Request;

@Controller
@RequestMapping("/request")
public class RequestController {

	private RequestDao requestDao;
	private ElderlyDao elderlyDao;
	private ContractDao contractDao;

	@Autowired
	public void serContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	@Autowired
	public void setElderlyDao(ElderlyDao elderlyDao) {
		this.elderlyDao = elderlyDao;
	}

	@Autowired
	public void setRequestDao(RequestDao requestDao) {
		this.requestDao = requestDao;
	}

	// Operacions: Crear, llistar, actualitzar, esborrar

	@RequestMapping("/list")
	public String listRequests(Model model, HttpSession session) {
		if (isCasCommittee(session)) {
			model.addAttribute("requests", requestDao.getAllRequestStateDone());
			return "request/list";
		}

		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping("/listforcompany")
	public String listForCompanyRequests(Model model, HttpSession session) {
		if (isCompany(session)) {
			User user = (User) session.getAttribute("user");
			model.addAttribute("requests", requestDao.getRequestByCIF(user.getUsername()));
			return "request/listforcompany";
		}

		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/add")
	public String addRequest(Model model, HttpSession session) {
		if(isElderly(session)) {
			model.addAttribute("request", new Request());
			model.addAttribute("elderlys", elderlyDao.getElderlys());
			return "request/add";
		}
		
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("request") Request request, BindingResult bindingResult) {
		RequestValidator requestValidator = new RequestValidator();
		requestValidator.validate(request, bindingResult);
		if (bindingResult.hasErrors())
			return "request/add";
		requestDao.addRequest(request);
		return "redirect:/";
	}

	@RequestMapping(value = "/update/{number}", method = RequestMethod.GET)
	public String editRequest(Model model, @PathVariable int number, HttpSession session) {
		if (isCasCommittee(session) || isElderly(session)) {
			model.addAttribute("request", requestDao.getRequest(number));
			model.addAttribute("contracts", contractDao.getAllContract());
			return "request/update";
		}
		
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("request") Request request, BindingResult bindingResult) {
		RequestValidator requestValidator = new RequestValidator();
		requestValidator.validate(request, bindingResult);
		if (bindingResult.hasErrors())
			return "request/update";
		requestDao.updateRequest(request);
		return "redirect:list";
	}

	@RequestMapping(value = "/updateforcompany/{number}", method = RequestMethod.GET)
	public String editRequestForCompany(Model model, @PathVariable int number, HttpSession session) {
		if (isCompany(session)) {
			model.addAttribute("request", requestDao.getRequest(number));
			model.addAttribute("contracts", contractDao.getAllContract());
			return "request/updateforcompany";
		}
		
		model.addAttribute("user", new User());
		return "login";
		
	}

	@RequestMapping(value = "/updateforcompany", method = RequestMethod.POST)
	public String processUpdateSubmitCompany(@ModelAttribute("request") Request request, BindingResult bindingResult) {
		RequestValidator requestValidator = new RequestValidator();
		requestValidator.validate(request, bindingResult);
		if (bindingResult.hasErrors())
			return "request/updateforcompany";
		requestDao.updateRequest(request);
		System.out.println("Mensaje para elderly: Se le acaba de asignar una fecha y hora para el servicio solicitado. A continuacion podra visualizarla: "+	 request.getSchedule());
		return "redirect:listforcompany";
	}
	
	@RequestMapping(value = "/updateforelderly/{number}", method = RequestMethod.GET)
	public String editRequestForElderly(Model model, @PathVariable int number, HttpSession session) {
		if (isElderly(session)) {
			model.addAttribute("request", requestDao.getRequest(number));
			model.addAttribute("contracts", contractDao.getAllContract());
			return "request/updateforelderly";
		}
		
		model.addAttribute("user", new User());
		return "login";
		
	}

	@RequestMapping(value = "/updateforelderly", method = RequestMethod.POST)
	public String processUpdateSubmitElderly(@ModelAttribute("request") Request request, BindingResult bindingResult) {
		RequestValidator requestValidator = new RequestValidator();
		requestValidator.validate(request, bindingResult);
		if (bindingResult.hasErrors())
			return "request/updateforelderly";
		requestDao.updateRequest(request);
		return "redirect:../elderly/indexelderly";
	}

	@RequestMapping(value = "/delete/{number}")
	public String processDelete(@PathVariable int number) {
		requestDao.deleteRequest(number);
		return "redirect:../list";
	}

	private boolean isCasCommittee(HttpSession session) {
		User login = (User) session.getAttribute("user");
		return (login != null && login.getRole().equals("casCommittee"));
	}

	private boolean isCompany(HttpSession session) {
		User login = (User) session.getAttribute("user");
		return (login != null && login.getRole().equals("company"));
	}
	
	private boolean isElderly(HttpSession session) {
		User login = (User) session.getAttribute("user");
		return (login != null && login.getRole().equals("elderly"));
	}

}