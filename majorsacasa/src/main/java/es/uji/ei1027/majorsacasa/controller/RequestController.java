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
   private ContractDao	contractDao;
   
   @Autowired
   public void serContractDao(ContractDao contractDao) {
	   this.contractDao=contractDao;
   }
   
   @Autowired
	public void setElderlyDao(ElderlyDao elderlyDao) {
		this.elderlyDao=elderlyDao;
	}
	

   @Autowired
   public void setRequestDao(RequestDao requestDao) { 
       this.requestDao=requestDao;
   }

   // Operacions: Crear, llistar, actualitzar, esborrar
   
   @RequestMapping("/list")
   public String listRequests(Model model) {
      model.addAttribute("requests", requestDao.getAllRequest());
      return "request/list";
   }
   
   @RequestMapping("/listforcompany")
   public String listForCompanyRequests(Model model, HttpSession session) {
	   User user = (User) session.getAttribute("user");
      model.addAttribute("requests", requestDao.getRequestByCIF(user.getUsername()));
      return "request/listforcompany";
   }
   
   @RequestMapping(value="/add") 
	public String addRequest(Model model) {
		model.addAttribute("request", new Request());
		model.addAttribute("elderlys", elderlyDao.getElderlys());
		return "request/add";
	}
   @RequestMapping(value="/add", method=RequestMethod.POST)
   public String processAddSubmit(@ModelAttribute("request") Request request,
                                   BindingResult bindingResult) {  
	   RequestValidator requestValidator = new RequestValidator();
	   requestValidator.validate(request, bindingResult);
   	 if (bindingResult.hasErrors()) 
   			return "request/add";
   	 requestDao.addRequest(request);
   	 return "redirect:list"; 
    }


   @RequestMapping(value="/update/{number}", method = RequestMethod.GET) 
	public String editRequest(Model model, @PathVariable int number) { 
		model.addAttribute("request", requestDao.getRequest(number));
		model.addAttribute("contracts", contractDao.getAllContract());
		return "request/update"; 
	}
   
   
   @RequestMapping(value="/update", method = RequestMethod.POST) 
	public String processUpdateSubmit(
                           @ModelAttribute("request") Request request, 
                           BindingResult bindingResult) {
	   RequestValidator requestValidator = new RequestValidator();
	   requestValidator.validate(request, bindingResult);
		 if (bindingResult.hasErrors()) 
			 return "request/update";
		 requestDao.updateRequest(request);
		 return "redirect:list"; 
	}
   
   @RequestMapping(value="/updateforcompany/{number}", method = RequestMethod.GET) 
 	public String editRequestForCompany(Model model, @PathVariable int number) { 
 		model.addAttribute("request", requestDao.getRequest(number));
 		model.addAttribute("contracts", contractDao.getAllContract());
 		return "request/updateforcompany"; 
  }
   
   @RequestMapping(value="/updateforcompany", method = RequestMethod.POST) 
	public String processUpdateSubmitCompany(
                          @ModelAttribute("request") Request request, 
                          BindingResult bindingResult) {
	   RequestValidator requestValidator = new RequestValidator();
	   requestValidator.validate(request, bindingResult);
		 if (bindingResult.hasErrors()) 
			 return "request/updateforcompany";
		 requestDao.updateRequest(request);
		 return "redirect:listforcompany"; 
	}
  
   @RequestMapping(value="/delete/{number}")
	public String processDelete(@PathVariable int number) {
          requestDao.deleteRequest(number);
          return "redirect:../list"; 
	}

}