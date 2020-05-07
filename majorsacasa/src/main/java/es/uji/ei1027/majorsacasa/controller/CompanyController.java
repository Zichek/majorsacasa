package es.uji.ei1027.majorsacasa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.dao.UserDao;
import es.uji.ei1027.majorsacasa.model.Company;


@Controller
@RequestMapping("/company") 
public class CompanyController {

   private CompanyDao companyDao;
   private UserDao userDao;

   @Autowired
   public void setCompanyDao(CompanyDao companyDao) { 
       this.companyDao=companyDao;
   }
   @Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao=userDao;
	}

   // Operacions: Crear, llistar, actualitzar, esborrar
   
   @RequestMapping("/list")
   public String listCompanys(Model model) {
      model.addAttribute("companys", companyDao.getAllCompany());
      return "company/list";
   }
   
   @RequestMapping(value="/add") 
	public String addCompany(Model model) {
		model.addAttribute("company", new Company());
		return "company/add";
	}
   @RequestMapping(value="/add", method=RequestMethod.POST)
   public String processAddSubmit(@ModelAttribute("company") Company company,
                                   BindingResult bindingResult) { 
	  CompanyValidator companyValidator = new CompanyValidator();
	  companyValidator.validate(company, bindingResult);
   	 if (bindingResult.hasErrors()) 
   			return "company/add";
   	 
   	 userDao.addUser(company.getCIF(), company.getPassword(), "company");
   	 companyDao.addCompany(company);
   	 return "redirect:list"; 
    }


   @RequestMapping(value="/update/{CIF}", method = RequestMethod.GET) 
	public String editCompany(Model model, @PathVariable String CIF) { 
		model.addAttribute("company", companyDao.getCompany(CIF));
		return "company/update"; 
	}
   @RequestMapping(value="/update", method = RequestMethod.POST) 
	public String processUpdateSubmit(
                           @ModelAttribute("company") Company company, 
                           BindingResult bindingResult) {
	   CompanyValidator companyValidator = new CompanyValidator();
	   companyValidator.validate(company, bindingResult);
		 if (bindingResult.hasErrors()) 
			 return "company/update";
		 userDao.updateUser(company.getCIF(), company.getPassword());
		 companyDao.updateCompany(company);
		 return "redirect:list"; 
	}

   @RequestMapping(value="/delete/{CIF}")
	public String processDelete(@PathVariable String CIF) {
	   		userDao.deleteUser(CIF);
          companyDao.deleteCompany(CIF);
         
          
          return "redirect:../list"; 
	}

}

