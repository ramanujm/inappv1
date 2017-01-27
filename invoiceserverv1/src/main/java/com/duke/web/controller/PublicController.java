package com.duke.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.duke.presentation.beans.DropDownItemVO;
import com.duke.presentation.beans.RegisterVO;
import com.duke.presentation.beans.Response;
import com.duke.service.DataService;
import com.duke.service.InvoiceService;
import com.duke.service.exception.EmailAlreadyExistsException;
import com.duke.service.exception.InvalidPasswordException;
import com.duke.service.exception.UserNotFoundException;



/**
 * Main controller for all business related activity.
 * @author root
 *
 */
@RestController
@RequestMapping("/public/")
public class PublicController {

	@Autowired
	InvoiceService invoiceService;	
	
	@Autowired
	DataService dataService;
	
	@RequestMapping(method = RequestMethod.POST,value="/register")
	public @ResponseBody Response doRegister(@RequestBody RegisterVO registerRq) {  
    	Response response = new Response();

      try{
    	invoiceService.registerUser(registerRq);    
    	response.setResult(Response.SUCCESS);
        response.setCode("001");
      }catch(EmailAlreadyExistsException e) {
    	  e.printStackTrace();
    	  response.setResult(Response.FAILED);
          response.setCode("002"); //email already exists..
          response.setDescription(e.getMessage());
      }catch (Exception e) {
    	  response.setResult(Response.FAILED);
          response.setCode("003"); //unknow exceptional condtion...
          response.setDescription(e.getMessage());

      }
               
	   return response;
	}
    

	   	@RequestMapping(method = RequestMethod.GET,value="/allCountries")
   	public @ResponseBody Response getAllCountries() {  
       	Response response = new Response();

         try{
        	 List<DropDownItemVO> lists = dataService.getAllCountries();    
       	    response.setResult(Response.SUCCESS);
           response.setCode("001");
           response.setResultData(lists);
         }
         catch (Exception e) {
       	  response.setResult(Response.FAILED);
             response.setCode("004"); //unknow exceptional condtion...
             response.setDescription(e.getMessage());
         }
                  
   	   return response;
   	}
    
    
   	@RequestMapping(method = RequestMethod.GET,value="/allIndustries")
   	public @ResponseBody Response getAllIndustries() {  
       	Response response = new Response();

         try{
        	 List<DropDownItemVO> lists = dataService.getAllIndustry();    
       	    response.setResult(Response.SUCCESS);
           response.setCode("001");
           response.setResultData(lists);
         }
         catch (Exception e) {
       	  response.setResult(Response.FAILED);
             response.setCode("004"); //unknow exceptional condtion...
             response.setDescription(e.getMessage());
         }
                  
   	   return response;
   	}
    
    
//    @CrossOrigin(origins = "*")
//    @PostMapping("/mycompany")    
//	public @ResponseBody Response editCompanyDetail(@RequestBody CompanyDetailVO companyDetailRq) {    
//        System.out.println("Inside the reponse....");   
//    	Response response = new Response();
//        	response.setResult("Success");
//        	response.setCode("001");
//       
//	   return response;
//	}
//    
//    @CrossOrigin(origins = "*")
//    @PostMapping("/myaccount")    
//	public @ResponseBody Response editAccountDetail(@RequestBody  PersonalInfoVO registerRq) {    
//        System.out.println("Inside the reponse....");   
//    	Response response = new Response();
//        	response.setResult("Success");
//        	response.setCode("001");
//       
//	   return response;
//	}
//    
//    @CrossOrigin(origins = "*")
//    @GetMapping("/mycompany")    
//	public @ResponseBody CompanyDetailVO getCompanyDetail() {    
//        System.out.println("Inside the reponse....");   
//    	CompanyDetailVO companyDetail = new CompanyDetailVO();
//    	companyDetail.setAddress("2928 tradewind dr");
//    	companyDetail.setTaxNo("98178676");
//    	companyDetail.setCity("Toronto");
//    	companyDetail.setPhone("6477091839");
//    	companyDetail.setProvince("ON");
//    	try{
//    	invoiceService.prepareInvoice(null);
//    	}catch(Exception e){
//    		
//    	}
//    	return companyDetail;
//	}
//    
//    @CrossOrigin(origins = "*")
//    @GetMapping("/myaccount")    
//	public @ResponseBody PersonalInfoVO getAccountDetail() {    
//        System.out.println("Inside the reponse....");   
//        PersonalInfoVO personalInfo = new PersonalInfoVO();
//        personalInfo.setFname("Parthiv");
//        personalInfo.setLname("Patel");
//        personalInfo.setLoginEmail("Contact2Parthiv@gmail.com");
//        personalInfo.setRegion("Canada");
//       return personalInfo;
//	}
	
		
}
