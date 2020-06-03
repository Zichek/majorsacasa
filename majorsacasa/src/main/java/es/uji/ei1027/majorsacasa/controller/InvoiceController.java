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
import es.uji.ei1027.majorsacasa.dao.InvoiceDao;
import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.model.Elderly;
import es.uji.ei1027.majorsacasa.model.Invoice;
import es.uji.ei1027.majorsacasa.model.User;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

	private InvoiceDao invoiceDao;
	private ElderlyDao elderlyDao;
	private RequestDao requestDao;

	@Autowired
	public void setInvoiceDao(InvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
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
	public String listCompanys(Model model, HttpSession session) {
		if (isCasCommittee(session)) {
			model.addAttribute("invoices", invoiceDao.getAllInvoice());
			return "invoice/list";
		}

		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/add")
	public String addInvoice(Model model, HttpSession session) {
		if (isCasCommittee(session)) {
			model.addAttribute("invoice", new Invoice());
			model.addAttribute("elderlies", elderlyDao.getElderlys());
			model.addAttribute("requests", requestDao.getAllRequest());
			return "invoice/add";
		}
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("invoice") Invoice invoice, BindingResult bindingResult) {
		InvoiceValidator invoiceValidator = new InvoiceValidator();
		invoiceValidator.validate(invoice, bindingResult);
		if (bindingResult.hasErrors())
			return "invoice/add";
		invoiceDao.addInvoice(invoice);
		return "redirect:list";
	}

	@RequestMapping(value = "/update/{number}", method = RequestMethod.GET)
	public String editInvoice(Model model, @PathVariable int number, HttpSession session) {
		if (isCasCommittee(session)) {
			model.addAttribute("invoice", invoiceDao.getInvoice(number));
			return "invoice/update";
		}
		
		model.addAttribute("user", new User());
		return "login";
	}
		

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("invoice") Invoice invoice, BindingResult bindingResult) {
		InvoiceValidator invoiceValidator = new InvoiceValidator();
		invoiceValidator.validate(invoice, bindingResult);
		if (bindingResult.hasErrors())
			return "invoice/update";
		invoiceDao.updateInvoice(invoice);
		;
		return "redirect:list";
	}

	@RequestMapping(value = "/delete/{number}")
	public String processDelete(@PathVariable int number) {
		invoiceDao.deleteInvoice(number);
		return "redirect:../list";
	}

	private boolean isCasCommittee(HttpSession session) {
		User login = (User) session.getAttribute("user");
		return (login != null && login.getRole().equals("casCommittee"));
	}

}