package com.duke.web.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.duke.presentation.beans.CustomerDetailVO;
import com.duke.presentation.beans.DropDownItemVO;
import com.duke.presentation.beans.PersonalInfoVO;
import com.duke.presentation.beans.RegisterVO;
import com.duke.presentation.beans.Response;
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
@RequestMapping("/user")
public class UserController {

	@Autowired
	InvoiceService invoiceService;
	
	@RequestMapping(method = RequestMethod.GET,value="/mydetails")
	public @ResponseBody Response getMyDetails(Principal principal) {  
    	Response response = new Response();
    	
     try{
    	PersonalInfoVO personalDetail = invoiceService.getUserDetail(principal.getName());    
    	response.setResult(Response.SUCCESS);
        response.setCode("001");
        response.setResultData(personalDetail);
      }catch(UserNotFoundException e) {
    	  e.printStackTrace();
    	  response.setResult(Response.FAILED);
          response.setCode("002"); //Email is not exists.
          response.setDescription(e.getMessage());
      }catch (Exception e) {
    	  response.setResult(Response.FAILED);
          response.setCode("003"); //unknow exceptional condtion...
          response.setDescription(e.getMessage());

      }
               
	   return response;
	}
    
    
   	@RequestMapping(method = RequestMethod.POST,value="/mydetails")
   	public @ResponseBody Response updatePersonalDetail(Principal princial,@RequestBody PersonalInfoVO personalInfo) {  
       	Response response = new Response();

         try{
        invoiceService.editUser(princial.getName(), personalInfo);    
       	response.setResult(Response.SUCCESS);
           response.setCode("001");
         }catch(UserNotFoundException e) {
       	  e.printStackTrace();
       	  response.setResult(Response.FAILED);
             response.setCode("002"); //Email is not exists.
             response.setDescription(e.getMessage());
         }catch (Exception e) {
       	  response.setResult(Response.FAILED);
             response.setCode("003"); //unknow exceptional condtion...
             response.setDescription(e.getMessage());

         }
                  
   	   return response;
   	}
   	
   	
    
   	@RequestMapping(method = RequestMethod.GET,value="/myCustomers")
   	public @ResponseBody Response myCustomers(Principal principal) {  
   		Response response = new Response();

        try{
        List<CustomerDetailVO> customers= invoiceService.getMyCustomers(principal.getName());    
      	response.setResult(Response.SUCCESS);
          response.setCode("001");
          response.setResultData(customers);
        }catch(UserNotFoundException e) {
      	  e.printStackTrace();
      	  response.setResult(Response.FAILED);
            response.setCode("002"); //Email is not exists.
            response.setDescription(e.getMessage());
        }catch (Exception e) {
      	  response.setResult(Response.FAILED);
            response.setCode("003"); //unknow exceptional condtion...
            response.setDescription(e.getMessage());
        }
   	   return response;
   	}
   	
   	
	@RequestMapping(method = RequestMethod.GET,value="/customerDetail")
   	public @ResponseBody Response getCustomer(Principal principal, String customerEmail) {  
   		Response response = new Response();

        try{
        CustomerDetailVO cusotmer= invoiceService.getCustomerDetail(principal.getName(), customerEmail);    
      	response.setResult(Response.SUCCESS);
          response.setCode("001");
          response.setResultData(cusotmer);
        }catch(UserNotFoundException e) {
      	  e.printStackTrace();
      	  response.setResult(Response.FAILED);
            response.setCode("002"); //Email is not exists.
            response.setDescription(e.getMessage());
        }catch (Exception e) {
      	  response.setResult(Response.FAILED);
            response.setCode("003"); //unknow exceptional condtion...
            response.setDescription(e.getMessage());
        }
   	   return response;
   	}
	
	
	@RequestMapping(method = RequestMethod.POST,value="/customerDetail")
   	public @ResponseBody Response getCustomer(Principal principal, @RequestBody CustomerDetailVO customerdetail) {  
   		Response response = new Response();

        try{
          invoiceService.updateCustomerDetail(principal.getName(), customerdetail);    
      	  response.setResult(Response.SUCCESS);
          response.setCode("001");
          //response.setResultData(cusotmer);
        }catch(UserNotFoundException e) {
      	  e.printStackTrace();
      	  response.setResult(Response.FAILED);
           response.setCode("002"); //Email is not exists.
           response.setDescription(e.getMessage());
        }catch (Exception e) {
      	  response.setResult(Response.FAILED);
            response.setCode("003"); //unknow exceptional condtion...
            response.setDescription(e.getMessage());
        }
   	   return response;
   	}	
}
