package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import java.util.List;


public class CompanyValidator implements Validator {
	
	@Override
    public boolean supports(Class<?> cls) {
        return Company.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Company company = (Company) obj;
        CompanyDao companyDao = new CompanyDao();
        List<Company> listCompany = companyDao.getAllCompany();

        for(Company company2 : listCompany){
            if(company2.getCIF().equals(company.getCIF()))
                errors.rejectValue("CIF", "required",
                        "Existing CIF");
        }

        if (company.getName().equals(""))
            errors.rejectValue("name", "required",
                    "Enter a valid value");

        if (company.getCIF().equals("") || company.getCIF().length() != 9)
            errors.rejectValue("cif", "required",
                    "Enter a valid value. It must be 9 characters");


        if (company.getAddress().equals(""))
            errors.rejectValue("address", "required",
                    "Enter a valid value");

        if (company.getContactPersonPhoneNumber().equals("") || company.getContactPersonPhoneNumber().length() != 9)
            errors.rejectValue("contactPersonPhoneNumber", "required",
                    "It must be 9 numbers.");

        if (company.getContactPersonEmail().equals(""))
            errors.rejectValue("contactPersonEmail", "required",
                    "Enter a valid value");

        if (company.getContactPersonName().equals(""))
            errors.rejectValue("contactPersonName", "required",
                    "Enter a valid value");

        if (company.getServiceType().equals(""))
            errors.rejectValue("serviceType", "obligatori",
                    "Cal introduir un valor");

    }


}