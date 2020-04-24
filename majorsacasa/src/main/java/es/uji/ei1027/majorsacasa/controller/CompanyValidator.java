//package es.uji.ei1027.majorsacasa.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import es.uji.ei1027.majorsacasa.model.Company;
//
//
//public class CompanyValidator implements Validator {
//  @Override
//  public boolean supports(Class<?> cls) {
//	  return Company.class.equals(cls);
//	 // o, si volguérem tractar també les subclasses: 
//	 // return Nadador.class.isAssignableFrom(cls);
//   }
// 
//  @Override
//  public void validate(Object obj, Errors errors) {
//	 Company company = (Company)obj;
//	
//	 List<String> serviceTypeList = Arrays.asList("MenjarsADomicili","ServeiSanitari","Neteja");
//	   if (!serviceTypeList.contains(company.getServiceType())) 
//	       errors.rejectValue("serviceType", "valor incorrecte",
//	               "Deus seleccionar una opció.");   
//   }
//}