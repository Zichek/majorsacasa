package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.InvoiceDao;
import es.uji.ei1027.majorsacasa.model.Invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class InvoiceValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return Invoice.class.equals(cls);
    }

    private InvoiceDao invoiceDao;
    @Autowired
    public void setInvoiceDao(InvoiceDao invoiceDao) {
    	this.invoiceDao=invoiceDao;
    }
    
    @Override
    public void validate(Object obj, Errors errors) {
        Invoice invoice = (Invoice) obj;
   
//        List<Invoice> listInvoice = invoiceDao.getAllInvoice();
//
//        for(Invoice invoice2 : listInvoice){
//            if(invoice2.getNumber()==(invoice.getNumber()))
//                errors.rejectValue("number", "request",
//                        "Existing number");
//        }

        if (invoice.getNumber()<=0)
            errors.rejectValue("number", "request",
                    "Enter a valid value");

        if (invoice.getElderlyDNI().equals("") || invoice.getElderlyDNI().length() != 9)
            errors.rejectValue("elderlyDNI", "request",
                    "Enter a valid value. It must be 9 characters");

        if (invoice.getDate()==null)
            errors.rejectValue("date", "request",
                    "Enter a valid value");

        if (invoice.getAmount() <= 0)
        	errors.rejectValue("amount", "request",
                    "Enter a valid value");
        
        if (invoice.getRequestNumber() <= 0)
        	errors.rejectValue("requestNumber", "request",
                    "Enter a valid value");
    }
}