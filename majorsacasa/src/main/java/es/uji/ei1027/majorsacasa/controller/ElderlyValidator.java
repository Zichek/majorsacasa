package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.ElderlyDao;
import es.uji.ei1027.majorsacasa.model.Elderly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class ElderlyValidator implements Validator {
    
	private ElderlyDao elderlyDao;
	
	@Autowired
	public void setElderlyDao(ElderlyDao elderlyDao) {
		this.elderlyDao=elderlyDao;
	}
	
	@Override
    public boolean supports(Class<?> cls) {
        return Elderly.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Elderly elderly = (Elderly) obj;
   
//        List<Elderly> listElderly = elderlyDao.getElderlys();
//        for (Elderly elderly2 : listElderly) {
//            if (elderly2.getDNI().equals(elderly.getDNI()))
//                errors.rejectValue("DNI", "required",
//                        "Existing DNI");
//        }
        if (elderly.getDNI().trim().equals("") || elderly.getDNI().length() != 9)
            errors.rejectValue("DNI", "required",
                    "Enter a valid value. It must be 9 characters");
        
        if (elderly.getName().trim().equals("") || elderly.getName().length()>20)
            errors.rejectValue("name", "required",
                    "Enter a valid value. It must be 20 characters");
       
        if (elderly.getSurname().equals("") || elderly.getSurname().length() > 20)
            errors.rejectValue("surname", "required",
                    "Enter a valid value. It must be 20 characters");
        
        if(elderly.getBirthDate()==null)
        	errors.rejectValue("birthDate", "required",
                    "Enter a valid value");
        
        if (elderly.getAddress().equals("") || elderly.getAddress().length() > 30)
            errors.rejectValue("address", "required",
                    "Enter a valid value. It must be 30 characters");
        
        if (elderly.getPhoneNumber().equals("") || elderly.getPhoneNumber().length() != 9)
            errors.rejectValue("phoneNumber", "required",
                    "Enter a valid value. It must be 9 characters");
        
        if (elderly.getBankAccountNumber().equals(""))
            errors.rejectValue("bankAccountNumber", "required",
                    "Enter a valid value");
        
        if (elderly.getEmail().equals(""))
            errors.rejectValue("email", "required",
                    "Enter a valid value");
        
        if(elderly.getUserPwd().trim().equals("") || elderly.getUserPwd().length() > 20)
        	errors.rejectValue("userPwd", "required",
                    "Enter a valid value. It must be 20 characters");
        
        if (elderly.getDateCreation() == null)
        	errors.rejectValue("dateCreation", "required", "Enter a valid value");
    }
}