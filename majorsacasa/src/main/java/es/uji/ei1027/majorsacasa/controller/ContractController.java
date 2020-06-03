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

import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.User;

@Controller
@RequestMapping("/contract")
public class ContractController {

	private ContractDao contractDao;
	private CompanyDao companyDao;

	@Autowired
	public void setContractDao(ContractDao contractDao) {
		this.contractDao = contractDao;
	}

	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	// Operacions: Crear, llistar, actualitzar, esborrar

	@RequestMapping("/list")
	public String listContracts(Model model, HttpSession session) {
		if (isCasManager(session)) {
			model.addAttribute("contracts", contractDao.getAllContract());
			return "contract/list";
		}
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/add")
	public String addContract(Model model, HttpSession session) {
		if (isCasManager(session)) {
			model.addAttribute("contract", new Contract());
			model.addAttribute("companies", companyDao.getAllCompany());
			return "contract/add";
		}
		
		model.addAttribute("user", new User());
		return "login";		
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("contract") Contract contract, BindingResult bindingResult) {
		ContractValidator contractValidator = new ContractValidator();
		contractValidator.validate(contract, bindingResult);
		if (bindingResult.hasErrors())
			return "contract/add";

		contractDao.addContract(contract);
		return "redirect:list";
	}

	@RequestMapping(value = "/update/{number}", method = RequestMethod.GET)
	public String editContract(Model model, @PathVariable int number, HttpSession session) {
		if (isCasManager(session)) {
			model.addAttribute("contract", contractDao.getContract(number));
			return "contract/update";
		}
		
		model.addAttribute("user", new User());
		return "login";
		
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("contract") Contract contract, BindingResult bindingResult) {

		ContractValidator contractValidator = new ContractValidator();
		contractValidator.validate(contract, bindingResult);
		if (bindingResult.hasErrors())
			return "contract/update";

		contractDao.updateContract(contract);
		return "redirect:list";
	}

	@RequestMapping(value = "/delete/{number}")
	public String processDelete(@PathVariable int number) {
		contractDao.deleteContract(number);
		return "redirect:../list";
	}
	
	private boolean isCasManager(HttpSession session) {
		User login = (User) session.getAttribute("user");
		return (login != null && login.getRole().equals("casManager"));
	}

}