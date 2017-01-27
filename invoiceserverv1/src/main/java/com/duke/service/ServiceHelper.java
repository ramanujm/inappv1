package com.duke.service;

import com.duke.db.beans.Address;
import com.duke.db.beans.Customer;
import com.duke.presentation.beans.AddressVO;
import com.duke.presentation.beans.CustomerDetailVO;

public class ServiceHelper {
	public static CustomerDetailVO getCustomerDetailVO(Customer customer) {
		if (customer == null) return null;
		
		
		CustomerDetailVO detailVo = new CustomerDetailVO();
		detailVo.setfName(customer.getFirstName());
		detailVo.setlName(customer.getLastName());
		detailVo.setCloseDate(customer.getSubscription().getCloseDate());
		detailVo.setEmail(customer.getEmail());
		detailVo.setJoinDate(customer.getSubscription().getJoinDate());
		detailVo.setPhone(customer.getPhone());
		detailVo.setAddressVO(getAddressVO ( customer.getAddress() ));
		
		return detailVo;
		
	}
	
	public static AddressVO getAddressVO(Address address) {
		if (address == null) return null;
		AddressVO addressVO = new AddressVO();
		addressVO.setAddress1(address.getAddress1());
		addressVO.setAddress2(address.getAddress2());
		addressVO.setCity(address.getCity());
		addressVO.setState(address.getCity());
		addressVO.setZip(address.getZip());
		addressVO.setId(address.getId().toString());
		return addressVO;
	}
	

}
