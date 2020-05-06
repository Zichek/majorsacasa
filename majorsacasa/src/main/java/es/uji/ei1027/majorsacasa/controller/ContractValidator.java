package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.Contract;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class ContractValidator implements Validator {

    public boolean supports(Class<?> cls) {
        return Contract.class.equals(cls);
    }


        public void validate(Object obj, Errors errors) {
            Contract contract = (Contract) obj;
            ContractDao contractDao = new ContractDao();
            List<Contract> listContract = contractDao.getAllContract();

            for(Contract contract2 : listContract){
                if(contract2.getNumber()==contract.getNumber())
                    errors.rejectValue("number", "required",
                            "Existing number");
            }

        if (contract.getCompanyCIF().equals("") || contract.getCompanyCIF().length() != 9)
            errors.rejectValue("cif", "required",
                    "It must be valid value");

        if (contract.getServiceType().equals(""))
            errors.rejectValue("serviceType", "required",
                    "It must be a valid value");

        if (contract.getPriceUnit()==0)
            errors.rejectValue("price", "required",
                    "It must be a valid value");

        if (contract.getDateBeginning()==null)
            errors.rejectValue("dateBegining", "required",
                    "It must be a valid value");

        if (contract.getDateEnding()==null)
            errors.rejectValue("dateEnding", "required",
                    "It must be a valid value");

        if (!contract.getDateEnding().after(contract.getDateBeginning())) {
            errors.rejectValue("dateEnding", "date", "\r\n" + 
            		"The end date must be later than the start date");
        }
    }
}