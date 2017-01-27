package com.duke.service;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hamcrest.CustomTypeSafeMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.duke.db.HibernateDao;
import com.duke.db.beans.Address;
import com.duke.db.beans.Business;
import com.duke.db.beans.BusinessUser;
import com.duke.db.beans.CountryMaster;
import com.duke.db.beans.Customer;
import com.duke.db.beans.Dao;
import com.duke.db.beans.IndustryMaster;
import com.duke.db.beans.Login;
import com.duke.db.beans.Subscription;
import com.duke.db.beans.UserDao;
import com.duke.db.beans.UserRoles;
import com.duke.db.beans.UserRolesId;
import com.duke.presentation.beans.AddressVO;
import com.duke.presentation.beans.CustomerDetailVO;
import com.duke.presentation.beans.DropDownItemVO;
import com.duke.presentation.beans.PersonalInfoVO;
import com.duke.presentation.beans.RegisterVO;
import com.duke.security.oauth.Constants;
import com.duke.service.exception.CustomerNotFound;
import com.duke.service.exception.EmailAlreadyExistsException;
import com.duke.service.exception.InvalidPasswordException;
import com.duke.service.exception.UserNotFoundException;

@Transactional
@Repository
public class InvoiceService extends BaseService {

	 public PersonalInfoVO getUserDetail(String email) throws UserNotFoundException, Exception {
		 Login existLogin =  getHibenrateDao().getByField("email", email, Login.class);
		 if (existLogin == null) {
			 throw new UserNotFoundException(email);
		 }
		 
		BusinessUser businessUser =  existLogin.getBusinessUser();
		
		PersonalInfoVO personalInfo = new PersonalInfoVO();
		//personalInfo.setLoginEmail(email);
		
		if (businessUser != null) {
			personalInfo.setFname(businessUser.getFirstName());
			personalInfo.setLname(businessUser.getLastName());			
		}
		return personalInfo;
	 }
		
	 public void editUser(String loginEmail , PersonalInfoVO personalInfo) throws UserNotFoundException, Exception {
			 Login existLogin =  getHibenrateDao().getByField("email", loginEmail, Login.class);
			 if (existLogin == null) {
				 throw new UserNotFoundException(loginEmail);
			 }
			 
			BusinessUser businessUser =  existLogin.getBusinessUser();
			
			if (businessUser == null) {
				 businessUser = new BusinessUser();
				existLogin.setBusinessUser(businessUser);
			}
			businessUser.setFirstName(personalInfo.getFname());
			businessUser.setLastName(personalInfo.getLname());
			
			getHibenrateDao().saveOrUpdate(businessUser);
			getHibenrateDao().saveOrUpdate(existLogin);
			
			
	 }
	 
	 
	 public void registerUser(RegisterVO registerVo) throws EmailAlreadyExistsException , Exception{
		 
		 Login existLogin =  getHibenrateDao().getByField("email", registerVo.getEmail(), Login.class);
		 if (existLogin != null) {
			 throw new EmailAlreadyExistsException(registerVo.getEmail());
		 }
		 
		 Login newLogin = new Login();
		 newLogin.setEmail(registerVo.getEmail());
		 newLogin.setPassword(registerVo.getPassword());
		 newLogin.setEnabled(true);
		 
		 UserRolesId roleId = new UserRolesId();
		 roleId.setLoginEmail(registerVo.getEmail());
		 roleId.setRole(Constants.USER_ROLE);//assign role
		 
		 UserRoles userRoles = new UserRoles();
		 userRoles.setId(roleId);
		 
		 Business business = new Business();
         Integer countryId = registerVo.getCountry() == null ? 1 :  Integer.valueOf(registerVo.getCountry())		;
         Integer industryId = registerVo.getIndustry() == null ? 1 : Integer.valueOf(registerVo.getIndustry());
       
		 CountryMaster countryMster =  getHibenrateDao().get(CountryMaster.class, countryId);
		 IndustryMaster indMaster =  getHibenrateDao().get(IndustryMaster.class, industryId);
		 
		 business.setCountryMaster(countryMster);
		 business.setIndustryMaster(indMaster);
		 
		 BusinessUser businessUser = new BusinessUser();
		 newLogin.setBusinessUser(businessUser);
		 
		 businessUser.setBusiness(business);
		 
		 if ( business.getSubscription() == null ) {
			 Subscription sub = new Subscription();
			 sub.setJoinDate(new Date());
			 business.setSubscription(sub);
		 }
		 
		 getHibenrateDao().save(business.getSubscription()); //save login details..

		 //save the login detail.
		 getHibenrateDao().save(businessUser); //save login details..
		 getHibenrateDao().save(business); //save login details..

		 getHibenrateDao().save(newLogin); //save login details..
		 getHibenrateDao().save(userRoles);//save roles
		 
	 }
	 
   
    
   
    
  public void updateCustomerDetail(String loginEmail, CustomerDetailVO customerDetail) throws UserNotFoundException, Exception {
	 
	 Customer customer = null; 
	  Login existLogin =  getHibenrateDao().getByField("email", loginEmail, Login.class);
	 if (existLogin == null) {
		 throw new UserNotFoundException(loginEmail);
	 }
	 
	 
	 if (customerDetail.getEmail() != null ) {
			 customer =getHibenrateDao().getByField("email", customerDetail.getEmail() , Customer.class);
	 }
	 
	 if (customer == null) {
		 customer = new Customer(); // looks like new customer..
	 }
	 
	 customer.setEmail(customerDetail.getEmail());
	 customer.setFirstName(customerDetail.getfName());
	 customer.setLastName(customerDetail.getlName());
	 customer.setPhone(customerDetail.getPhone());
	 if ( customer.getSubscription() == null ) {
		 Subscription sub = new Subscription();
		 sub.setJoinDate(new Date());
		 customer.setSubscription(sub);
	 }
     
	 Address address = customer.getAddress() == null ? new Address() : customer.getAddress();
	 
	 if (customerDetail.getAddressVO() != null) {
		 AddressVO addressVO = customerDetail.getAddressVO();
		 address.setAddress1(addressVO.getAddress1());
		 address.setAddress2(addressVO.getAddress2());
		 address.setCity(addressVO.getCity());
		 address.setState(addressVO.getState());
		 address.setZip(addressVO.getZip());
		 customer.setAddress(address); //set the address..
	 }
	 
	 existLogin.getBusinessUser().getBusiness().getCustomers().add(customer);
	 
	  getHibenrateDao().saveOrUpdate(customer.getSubscription());//save existing customer.
	  
	 getHibenrateDao().saveOrUpdate(customer.getAddress());//save existing customer.
	 getHibenrateDao().saveOrUpdate(existLogin.getBusinessUser().getBusiness());//save existing customer.
	 getHibenrateDao().saveOrUpdate(customer);//save existing customer.


	 
  }
    
  
  public CustomerDetailVO getCustomerDetail(String loginEmail, String customerEmail) throws UserNotFoundException, CustomerNotFound, Exception {
		 
	  Customer customer = null; 
	  Login existLogin =  getHibenrateDao().getByField("email", loginEmail, Login.class);
	 if (existLogin == null) {
		 throw new UserNotFoundException(loginEmail);
	 }
	 
	 if ( customerEmail == null) {
	    throw new CustomerNotFound(customerEmail);
	 }
	 
	 
	 if ( customerEmail != null ) {
		 //get the customer by id..
		try{
		 customer =getHibenrateDao().getByField("email", customerEmail, Customer.class);
		 if (customer == null) {
			    throw new CustomerNotFound(customerEmail);		 
		 }
		}catch(NumberFormatException e){
			     throw new CustomerNotFound(customerEmail);

		 }
	 }
	 return ServiceHelper.getCustomerDetailVO(customer);	 
  }
    
  
  public List<CustomerDetailVO> getMyCustomers(String loginEmail) throws UserNotFoundException, Exception {
	  
	  Login existLogin =  getHibenrateDao().getByField("email", loginEmail, Login.class);
		 if (existLogin == null) {
			 throw new UserNotFoundException(loginEmail);
		 }
	  
		 List<CustomerDetailVO> cusotmersVo= new ArrayList();
		 Iterator<Customer> customers = existLogin.getBusinessUser().getBusiness().getCustomers().iterator();
		 
		 while(customers.hasNext()) {
			 Customer customer = customers.next();
			 cusotmersVo.add(ServiceHelper.getCustomerDetailVO(customer));

		 }
		 
		
		 return cusotmersVo;
	  
    	
    }
    

    
}
