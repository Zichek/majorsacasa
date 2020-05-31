package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.dao.UserDao;
import es.uji.ei1027.majorsacasa.model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;


public class CompanyValidator implements Validator {
	
	public CompanyDao companyDao;
	 

	   @Autowired
	   public void setCompanyDao(CompanyDao companyDao) { 
	       this.companyDao=companyDao;
	   }
	
	@Override
    public boolean supports(Class<?> cls) {
        return Company.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Company company = (Company) obj;
        
//        List<Company> listCompany = companyDao.getAllCompany();
//        
//        
//        for(Company company2 : listCompany){
//            if(company2.getCIF().equals(company.getCIF()))
//                errors.rejectValue("CIF", "required",
//                        "Existing CIF");
//        }

        if (company.getName().trim().equals(""))
            errors.rejectValue("name", "required",
                    "Enter a valid value");
        
        if (company.getPassword().trim().equals(""))
            errors.rejectValue("password", "required",
                    "Enter a valid value");
        

        if (company.getCIF().trim().equals("") || company.getCIF().length() != 9)
            errors.rejectValue("CIF", "required",
                    "Enter a valid value. It must be 9 characters");


        if (company.getAddress().trim().equals(""))
            errors.rejectValue("address", "required",
                    "Enter a valid value");

        if (company.getContactPersonPhoneNumber().length() != 9)
            errors.rejectValue("contactPersonPhoneNumber", "required",
                    "It must be 9 numbers.");

        if (company.getContactPersonEmail().trim().equals(""))
            errors.rejectValue("contactPersonEmail", "required",
                    "Enter a valid value");

        if (company.getContactPersonName().trim().equals(""))
            errors.rejectValue("contactPersonName", "required",
                    "Enter a valid value");

       

    }


}