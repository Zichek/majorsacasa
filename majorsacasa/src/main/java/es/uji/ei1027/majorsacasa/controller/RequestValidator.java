package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Request.class.equals(cls);
      
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Request request = (Request) obj;
       

        if (request.getNumber() <=0)
            errors.rejectValue("number", "required",
                    "Enter a valid value");

       

        if (request.getElderlyDNI().equals("") || request.getElderlyDNI().length() != 9)
            errors.rejectValue("elderlyDNI", "required",
                    "Enter a valid value. It must be 9 characters");

    

        

        if (request.getCreationDate() == null)
            errors.rejectValue("creationDate", "required",
                    "Enter a valid value");

       
        
        if (request.getComments().trim().equals("") || request.getComments().length() > 100)
            errors.rejectValue("comments", "required",
                    "Enter a valid value");
        
        if (request.getEndDate() == null)
            errors.rejectValue("endDate", "required",
                    "Enter a valid value");
    }
}