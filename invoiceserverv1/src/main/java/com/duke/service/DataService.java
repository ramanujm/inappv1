package com.duke.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.duke.db.HibernateDao;
import com.duke.db.beans.CountryMaster;
import com.duke.db.beans.IndustryMaster;
import com.duke.presentation.beans.DropDownItemVO;

@Transactional
@Repository
public class DataService {

	 @Autowired
	 private HibernateDao hibenrateDao;
	 
	 public List<DropDownItemVO> getAllCountries() throws Exception {
	    	
	    	List<CountryMaster> countries = hibenrateDao.getAll(CountryMaster.class);
	    	
	    	if (countries.isEmpty()) return null;
	    	List<DropDownItemVO> dropDownItems = new ArrayList();
	    	for (CountryMaster country : countries) {
	    		DropDownItemVO item = new DropDownItemVO();
	    		item.setId(String.valueOf(country.getId()));
	    		item.setValue(country.getName());
	    	     dropDownItems.add(item);	
	    	}
	    	return dropDownItems;
	    	
	    }
	 
	 
	 public List<DropDownItemVO> getAllIndustry() throws Exception {
	    	
	    	List<IndustryMaster> induistries = hibenrateDao.getAll(IndustryMaster.class);
	    	
	    	if (induistries.isEmpty()) return null;
	    	List<DropDownItemVO> dropDownItems = new ArrayList();
	    	for (IndustryMaster industry : induistries) {
	    		DropDownItemVO item = new DropDownItemVO();
	    		item.setId(String.valueOf(industry.getId()));
	    		item.setValue(industry.getDescription());
	    	     dropDownItems.add(item);	
	    	}
	    	return dropDownItems;
	    	
	    }
	   
}
